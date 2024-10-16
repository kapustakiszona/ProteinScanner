package com.example.proteinscanner.presentation.main

import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proteinscanner.domain.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {
    val productList = mutableStateListOf<Product>()
    val prodId = mutableLongStateOf(0)
    private val _barcodeState = MutableStateFlow(BarcodeState())
    val barcodeState = _barcodeState.asStateFlow()

    fun startScanning() {
        viewModelScope.launch {
            repository.startScanning().collect { code ->
                if (!code.isNullOrBlank()){
                    _barcodeState.value = barcodeState.value.copy(
                        barcode = code
                    )
                }
            }
        }
    }


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