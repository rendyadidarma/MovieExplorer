package com.rainday.movieexplorer.core.network

import com.rainday.movieexplorer.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Bearer ${BuildConfig.TMDB_READ_TOKEN}")
            .addHeader("Content-Type", "application/json")
            .build()

        return chain.proceed(request)
    }
}