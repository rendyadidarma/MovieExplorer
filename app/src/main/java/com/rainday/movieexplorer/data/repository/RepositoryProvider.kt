package com.rainday.movieexplorer.data.repository

import com.rainday.movieexplorer.core.network.ApiClient
import com.rainday.movieexplorer.domain.repository.MovieRepository

object RepositoryProvider {

    val movieRepository: MovieRepository by lazy {
        MovieRepositoryImpl(
            api = ApiClient.tmdbApi
        )
    }
}