package com.example.proteinscanner.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductResponseDto(
    @SerialName("code")
    val id: String,
    @SerialName("product")
    val productDto: ProductDto,
//    val status: Int,
//    val status_verbose: String
)