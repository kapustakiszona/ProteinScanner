package com.example.proteinscanner.data.network.api

import com.example.proteinscanner.data.network.model.ProductResponseDto
import com.example.proteinscanner.data.util.DataState
import com.example.proteinscanner.domain.NetworkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor
    (private val productRemoteDataSource: ProductRemoteDataSource) :
    NetworkRepository {

    override suspend fun getProduct(barcode: String): Flow<DataState<ProductResponseDto>> = flow {
        productRemoteDataSource.getProduct(barcode = barcode)
    }
}