package com.rainday.movieexplorer.ui.detail

import androidx.compose.runtime.Immutable
import com.rainday.movieexplorer.ui.model.MovieDetailUiModel

@Immutable
data class DetailState(
    val isLoading: Boolean = false,
    val error: String = "",
    val movie: MovieDetailUiModel? = null
)