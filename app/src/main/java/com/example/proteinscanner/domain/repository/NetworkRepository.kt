package com.example.proteinscanner.domain.repository

import com.example.proteinscanner.data.network.model.ProductResponseDto
import com.example.proteinscanner.data.util.DataState
import kotlinx.coroutines.flow.Flow

interface NetworkRepository {
     fun getProduct(barcode: String): Flow<DataState<ProductResponseDto>>
}