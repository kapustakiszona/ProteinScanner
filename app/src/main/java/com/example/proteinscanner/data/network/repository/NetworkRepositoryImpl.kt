package com.example.proteinscanner.data.network.repository

import com.example.proteinscanner.data.network.model.ProductResponseDto
import com.example.proteinscanner.data.network.source.ProductRemoteDataSource
import com.example.proteinscanner.data.util.DataState
import com.example.proteinscanner.domain.repository.NetworkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(private val productRemoteDataSource: ProductRemoteDataSource) :
    NetworkRepository {

    override fun getProduct(barcode: String): Flow<DataState<ProductResponseDto>> = flow {
        emitAll(productRemoteDataSource.getProduct(barcode = barcode))
    }
}