package com.rainday.movieexplorer.domain.model

data class Review(
    val author: String,
    val content: String,
    val dateText: String,
    val ratingText: String?
)
