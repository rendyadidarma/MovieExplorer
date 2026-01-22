package com.rainday.movieexplorer.domain.repository

import com.rainday.movieexplorer.core.network.NetworkResult
import com.rainday.movieexplorer.domain.model.Genre
import com.rainday.movieexplorer.domain.model.Movie
import com.rainday.movieexplorer.domain.model.MovieDetail

interface MovieRepository {
    suspend fun getGenres(): NetworkResult<List<Genre>>

    suspend fun discoverMovies(
        genreId: String,
        page: Int
    ): NetworkResult<List<Movie>>

    suspend fun getMovieDetail(
        movieId: Int
    ): NetworkResult<MovieDetail>
}