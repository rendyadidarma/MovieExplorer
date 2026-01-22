package com.rainday.movieexplorer.ui.detail

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rainday.movieexplorer.core.network.NetworkResult
import com.rainday.movieexplorer.domain.repository.MovieRepository
import com.rainday.movieexplorer.ui.model.toUiModel
import com.rainday.movieexplorer.ui.model.toUiModels
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@Stable
class DetailViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    // “The data layer supports pagination, but for this scope I intentionally load the first page only because review volume is low and the feature is secondary. It’s easy to extend later.”
    val currentPage = 1

    private val _state = MutableStateFlow(DetailState())
    val state = _state.asStateFlow()

    fun onAction(action: DetailAction) {
        when (action) {
            is DetailAction.SetupInit -> {
                getMovieDetail(action.movieId)
                getReviews(action.movieId)
                getTrailer(action.movieId)
            }
        }
    }

    private fun getMovieDetail(movieId: Int) {
        _state.update {
            it.copy(
                isLoading = true
            )
        }
        viewModelScope.launch {
            when (val result = repository.getMovieDetail(movieId)) {
                is NetworkResult.Success -> {
                    _state.update {
                        it.copy(
                            movie = result.data.toUiModel(),
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

    private fun getReviews(movieId: Int) {
        _state.update {
            it.copy(
                isLoading = true
            )
        }
        viewModelScope.launch {
            when (val result = repository.getMovieReviews(movieId, currentPage)) {
                is NetworkResult.Success -> {
                    _state.update {
                        it.copy(
                            reviews = result.data.toUiModels(),
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

    private fun getTrailer(movieId: Int) {
        _state.update {
            it.copy(
                isLoading = true
            )
        }
        viewModelScope.launch {
            when (val result = repository.getMovieTrailer(movieId)) {
                is NetworkResult.Success -> {
                    _state.update {
                        it.copy(
                            trailer = result.data?.toUiModel(),
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