package com.rainday.movieexplorer.data.repository

import com.rainday.movieexplorer.data.mapper.toDomain
import com.rainday.movieexplorer.data.remote.api.TMDBApi
import com.rainday.movieexplorer.domain.model.Genre
import com.rainday.movieexplorer.domain.model.Movie
import com.rainday.movieexplorer.domain.model.MovieDetail
import com.rainday.movieexplorer.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val api: TMDBApi
): MovieRepository {
    override suspend fun getGenres(): List<Genre> {
        return api.getGenres()
            .map { it.toDomain() }
    }

    override suspend fun discoverMovies(
        genreId: String,
        page: Int
    ): List<Movie> {
        return api.discoverMovies(genreId, page)
            .results
            .map { it.toDomain() }
    }

    override suspend fun getMovieDetail(movieId: Int): MovieDetail {
        return api.getMovieDetail(movieId)
            .toDomain()
    }
}