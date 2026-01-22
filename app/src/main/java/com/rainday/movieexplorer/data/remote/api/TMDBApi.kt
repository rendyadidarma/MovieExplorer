package com.rainday.movieexplorer.data.remote.api

import com.rainday.movieexplorer.data.remote.dto.GenreResponseDto
import com.rainday.movieexplorer.data.remote.dto.MovieDetailDto
import com.rainday.movieexplorer.data.remote.dto.MovieResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBApi {

    @GET("genre/movie/list")
    suspend fun getGenres(): GenreResponseDto

    @GET("discover/movie")
    suspend fun discoverMovies(
        @Query("with_genres") genreId: String,
        @Query("page") page: Int
    ): MovieResponseDto

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int
    ): MovieDetailDto
}