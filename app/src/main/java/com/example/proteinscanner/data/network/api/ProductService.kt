package com.example.proteinscanner.data.network.api

import com.example.proteinscanner.data.network.model.ProductResponseDto
import com.example.proteinscanner.data.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductService {

    @GET("{barcode}")
    suspend fun getProductByBarcode(
        @Path(Constants.BARCODE) barcode: String,
        @Query("fields") fields: String = Constants.QUERY_PARAMS
    ): Response<ProductResponseDto>
}