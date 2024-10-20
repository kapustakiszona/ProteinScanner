package com.example.proteinscanner.domain

data class Product(
    val name: String,
    val image: String,
    val id: Long,
    val protein: Double,
    val fat: Double,
    val carbohydrates: Double,
    val calories: Double
)
