package com.rainday.movieexplorer.ui.genre

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rainday.movieexplorer.core.network.NetworkResult
import com.rainday.movieexplorer.domain.repository.MovieRepository
import com.rainday.movieexplorer.ui.model.toUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@Stable
class GenreViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(GenreState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                /** Load initial data here **/
                getGenres()
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = GenreState()
        )

    fun getGenres() {
        _state.update {
            it.copy(
                isLoading = true
            )
        }
        viewModelScope.launch {
            when (val result = repository.getGenres()) {
                is NetworkResult.Success -> {
                    _state.update {
                        it.copy(
                            genres = result.data.toUiModel(),
                            error = "",
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