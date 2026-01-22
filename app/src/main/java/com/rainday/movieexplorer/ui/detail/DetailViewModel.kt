package com.rainday.movieexplorer.ui.detail

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
class DetailViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state = _state.asStateFlow()

    fun onAction(action: DetailAction) {
        when (action) {
            is DetailAction.SetupInit -> getMovieDetail(action.movieId)
        }
    }

    fun getMovieDetail(movieId: Int) {
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
                            isLoading = false
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