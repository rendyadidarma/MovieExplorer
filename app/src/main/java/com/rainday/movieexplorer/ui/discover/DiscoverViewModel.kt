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
        }
    }

    fun getMovies(genreId: String, page: Int = 1) {
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
}