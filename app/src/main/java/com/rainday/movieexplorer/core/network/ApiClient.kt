package com.rainday.movieexplorer.core.network

import com.rainday.movieexplorer.data.remote.api.TMDBApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object ApiClient {

    private val okHttp = OkHttpClient.Builder()
        .addInterceptor(ApiInterceptor())
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .client(okHttp)
        .build()

    val tmdbApi: TMDBApi by lazy {
        retrofit.create(TMDBApi::class.java)
    }
}
