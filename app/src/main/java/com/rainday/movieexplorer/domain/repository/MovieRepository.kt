package com.rainday.movieexplorer.domain.repository

import com.rainday.movieexplorer.domain.model.Genre
import com.rainday.movieexplorer.domain.model.Movie
import com.rainday.movieexplorer.domain.model.MovieDetail

interface MovieRepository {
    suspend fun getGenres(): List<Genre>

    suspend fun discoverMovies(
        genreId: String,
        page: Int
    ): List<Movie>

    suspend fun getMovieDetail(
        movieId: Int
    ): MovieDetail
}