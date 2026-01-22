package com.rainday.movieexplorer.ui.discover

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rainday.movieexplorer.core.network.NetworkResult
import com.rainday.movieexplorer.domain.repository.MovieRepository
import com.rainday.movieexplorer.ui.model.toUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@Stable
class DiscoverViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    private val _state = MutableStateFlow(DiscoverState())
    val state = _state.asStateFlow()

    fun onAction(action: DiscoverAction) {
        when (action) {
            is DiscoverAction.SetupInit -> {
                _state.update { it.copy(genreId = action.genreId) }
                getMovies(action.genreId, action.page)
            }

            is DiscoverAction.LoadNext -> {
                loadNextPage(action.genreId)
            }
        }
    }

    private fun getMovies(genreId: String, page: Int = 1) {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            when (val result = repository.discoverMovies(genreId, page)) {
                is NetworkResult.Success -> {
                    _state.update {
                        it.copy(
                            movies = result.data.toUiModel(),
                            isLoading = false,
                            error = ""
                        )
                    }
                }

                is NetworkResult.Error -> {
                    _state.update {
                        it.copy(
                            error = result.error,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }


    private fun loadNextPage(genreId: String) {
        viewModelScope.launch {
            if (_state.value.isLoadingMore || _state.value.endReached) return@launch

            _state.update {
                it.copy(isLoadingMore = true)
            }
            when (val result = repository.discoverMovies(genreId, _state.value.page)) {
                is NetworkResult.Success -> {
                    val newMovies = result.data.map { it.toUiModel() }

                    val merged = (_state.value.movies + newMovies)
                        .distinctBy { it.id }

                    _state.update {
                        it.copy(
                            movies = merged,
                            page = _state.value.page + 1,
                            isLoadingMore = false,
                            endReached = newMovies.isEmpty(),
                            error = ""
                        )
                    }
                }

                is NetworkResult.Error -> {
                    _state.update {
                        it.copy(
                            isLoadingMore = false,
                            error = result.error
                        )
                    }
                }
            }
        }
    }
}