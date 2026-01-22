package com.rainday.movieexplorer.ui.model

data class MovieDetailUiModel(
    val title: String,
    val tagline: String?,
    val overview: String,
    val posterUrl: String?,
    val backdropUrl: String?,
    val releaseDateText: String,
    val runtimeText: String,
    val ratingText: String,
    val genresText: String,
    val homepage: String?
)