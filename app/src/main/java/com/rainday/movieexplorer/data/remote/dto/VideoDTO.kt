package com.rainday.movieexplorer.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class VideoResponseDto(
    val results: List<VideoDto>
)

@Serializable
data class VideoDto(
    val id: String,
    val key: String,
    val site: String,
    val type: String,
    val official: Boolean
)