package com.rainday.movieexplorer.ui.genre

import androidx.compose.runtime.Immutable
import com.rainday.movieexplorer.ui.model.GenreUiModel

@Immutable
data class GenreState(
    val genres: List<GenreUiModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)