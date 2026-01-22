package com.rainday.movieexplorer.ui.navigation

import androidx.navigation3.runtime.NavKey
import com.rainday.movieexplorer.ui.model.GenreUiModel
import kotlinx.serialization.Serializable

sealed interface Screen : NavKey {
    @Serializable
    data class Detail(val movieId: Int) : Screen

    @Serializable
    data class Discover(val genre: GenreUiModel) : Screen

    @Serializable
    data object Genre : Screen
}