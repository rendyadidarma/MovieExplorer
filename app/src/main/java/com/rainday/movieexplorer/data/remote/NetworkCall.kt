package com.rainday.movieexplorer.data.remote

import com.rainday.movieexplorer.core.network.NetworkResult
import retrofit2.HttpException
import java.io.IOException

suspend inline fun <T> safeApiCall(
    crossinline call: suspend () -> T
): NetworkResult<T> {
    return try {
        NetworkResult.Success(call())
    } catch (_: IOException) {
        NetworkResult.Error("Network error. Please check your connection.")
    } catch (e: HttpException) {
        NetworkResult.Error("Server error (${e.code()})")
    } catch (_: Exception) {
        NetworkResult.Error("Unexpected error")
    }
}