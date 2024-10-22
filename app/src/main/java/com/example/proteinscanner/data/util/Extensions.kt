package com.example.proteinscanner.data.util

import com.example.proteinscanner.data.network.model.ProductResponseDto
import com.example.proteinscanner.domain.model.Product

fun ProductResponseDto.toProduct() = Product(
    name = this.productDto.productName,
    image = this.productDto.image,
    id = this.id.toLong(),
    protein = this.productDto.proteins,
    fat = this.productDto.fat,
    carbohydrates = this.productDto.carbohydrates,

    )