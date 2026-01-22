package com.rainday.movieexplorer.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterUrl: String?,
    val backdropUrl: String?,
    val releaseDate: String?,
    val rating: Double,
    val voteCount: Int,
    val genreIds: List<Int>,
    val language: String,
    val popularity: Double,
    val isAdult: Boolean,
    val hasVideo: Boolean
)
