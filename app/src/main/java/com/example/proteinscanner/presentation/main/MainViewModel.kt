package com.example.proteinscanner.presentation.main

import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val productList = mutableStateListOf<Product>()
    val prodId = mutableLongStateOf(0)

    fun addProduct() {
            val newProduct = Product(
                name = "Name ${prodId.longValue}",
                description = "Description ${prodId.longValue}",
                image = "new_product_image.jpg ${prodId.longValue}",
                id = prodId.longValue
            )
        productList.add(newProduct)
        prodId.longValue++
    }
}