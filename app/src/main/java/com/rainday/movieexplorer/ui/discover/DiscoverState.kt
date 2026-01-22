package com.rainday.movieexplorer.ui.discover

import androidx.compose.runtime.Immutable
import com.rainday.movieexplorer.ui.model.MovieUiModel

@Immutable
data class DiscoverState(
    val isLoading: Boolean = false,
    val error: String = "",
    val movies: List<MovieUiModel> = emptyList(),
    val genreId: String = ""
)