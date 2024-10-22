package com.example.proteinscanner.domain.viewstate

import com.example.proteinscanner.data.network.model.ProductResponseDto

data class ProductViewState(
    val isLoading: Boolean = false,
    val data: List<ProductResponseDto>? = emptyList(),
):ViewState
