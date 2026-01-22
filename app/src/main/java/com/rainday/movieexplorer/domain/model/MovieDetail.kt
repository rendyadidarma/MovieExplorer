package com.rainday.movieexplorer.domain.model

data class MovieDetail(
    val id: Int,
    val title: String,
    val tagline: String?,
    val overview: String,
    val posterUrl: String?,
    val backdropUrl: String?,
    val releaseDate: String?,
    val runtimeMinutes: Int?,
    val rating: Double,
    val voteCount: Int,
    val genres: List<Genre>,
    val language: String,
    val popularity: Double,
    val isAdult: Boolean,
    val imdbId: String?,
    val homepage: String?
)