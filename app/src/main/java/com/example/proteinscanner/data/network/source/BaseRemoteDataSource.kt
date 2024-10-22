package com.example.proteinscanner.data.network.source

import android.util.Log
import com.example.proteinscanner.data.util.ApiError
import com.example.proteinscanner.data.util.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.serialization.json.Json
import retrofit2.Response

open class BaseRemoteDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Flow<DataState<T>> {
        return flow<DataState<T>> {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) emit(DataState.Success(data = body))
                else {
                    val apiError: ApiError =
                        Json.decodeFromString(response.errorBody()?.string() ?: "")
                    Log.d("zalupa", "getResult: error ${apiError.message}")
                    emit(DataState.Error(apiError = apiError))
                }
            } else {
                val apiError: ApiError =
                    Json.decodeFromString(response.errorBody()?.string() ?: "")
                Log.d("zalupa", "getResult: error ${apiError.message}")
                emit(DataState.Error(apiError = apiError))
            }
        }
            .catch {
                Log.d("zalupa", "getResult: error ${it.message}")
                emit(DataState.Error(apiError = ApiError(-1, it.message ?: it.toString())))
            }
            .onStart { emit(DataState.Loading()) }
            .flowOn(Dispatchers.IO)
    }
}