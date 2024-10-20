package com.example.proteinscanner.data.network.api

import com.example.proteinscanner.data.network.model.ProductResponseDto
import com.example.proteinscanner.data.util.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRemoteDataSource @Inject constructor(private val productService: ProductService) :
    BaseRemoteDataSource() {

    suspend fun getProduct(barcode: String): Flow<DataState<ProductResponseDto>> = getResult {
        productService.getProductByBarcode(barcode = barcode)
    }
}