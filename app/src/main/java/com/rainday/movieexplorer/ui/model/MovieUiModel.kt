package com.rainday.movieexplorer.ui.model

data class MovieUiModel(
    val id: Int,
    val title: String,
    val posterUrl: String?,
    val ratingText: String,
    val releaseYear: String?
)
