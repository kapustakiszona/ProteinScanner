package com.example.proteinscanner.data.util

sealed class DataState<T> {
    class Success<T>(val data: T) : DataState<T>()
    class Error<T>(val apiError: ApiError?) : DataState<T>()
    class Loading<T> : DataState<T>()
}