package com.example.proteinscanner.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    @SerialName("carbohydrates_100g")
    val carbohydrates: Double,
    @SerialName("fat_100g")
    val fat: Double,
    @SerialName("image_url")
    val image: String,
    @SerialName("proteins_100g")
    val proteins: Double,
    @SerialName("product_name")
    val productName: String,
)