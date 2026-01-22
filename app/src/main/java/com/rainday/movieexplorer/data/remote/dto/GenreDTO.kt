package com.rainday.movieexplorer.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class GenreResponseDto(
    val genres: List<GenreDto>
)


@Serializable
data class GenreDto(
    val id: String,
    val name: String
)
