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
        @Query("fields") fields: String =
            "product_name,nutriments{proteins_100g,carbohydrates_100g,fat_100g},product_url"
    ): Response<ProductResponseDto>
}