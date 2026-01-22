package com.rainday.movieexplorer.ui.model

import kotlinx.serialization.Serializable

@Serializable
data class GenreUiModel(
    val id: String,
    val name: String
)