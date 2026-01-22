package com.rainday.movieexplorer.ui.detail

import androidx.compose.runtime.Immutable
import com.rainday.movieexplorer.ui.model.MovieDetailUiModel
import com.rainday.movieexplorer.ui.model.MovieTrailerUiModel
import com.rainday.movieexplorer.ui.model.ReviewUiModel

@Immutable
data class DetailState(
    val isLoading: Boolean = false,
    val error: String = "",
    val movie: MovieDetailUiModel? = null,
    val trailer: MovieTrailerUiModel? = null,
    val reviews: List<ReviewUiModel> = emptyList()
)