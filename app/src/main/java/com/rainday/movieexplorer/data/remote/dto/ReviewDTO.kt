package com.rainday.movieexplorer.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReviewResponseDto(
    val page: Int,

    val results: List<ReviewDto>,

    @SerialName("total_pages")
    val totalPages: Int
)

@Serializable
data class ReviewDto(
    val id: String,
    val author: String,

    @SerialName("author_details")
    val authorDetails: AuthorDetailsDto,

    val content: String,

    @SerialName("created_at")
    val createdAt: String
)

@Serializable
data class AuthorDetailsDto(
    val rating: Double? = null
)