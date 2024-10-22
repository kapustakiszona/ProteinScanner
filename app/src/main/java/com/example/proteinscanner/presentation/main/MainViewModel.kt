package com.example.proteinscanner.presentation.main

import androidx.lifecycle.viewModelScope
import com.example.proteinscanner.data.util.DataState
import com.example.proteinscanner.domain.repository.NetworkRepository
import com.example.proteinscanner.domain.repository.ScannerRepository
import com.example.proteinscanner.domain.viewstate.ProductViewEvent
import com.example.proteinscanner.domain.viewstate.ProductViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor
    (
    private val networkRepository: NetworkRepository,
    private val scannerRepository: ScannerRepository
) : BaseViewModel<ProductViewState, ProductViewEvent>() {

    private fun getProductByBarcode(barcode: String) {
        viewModelScope.launch {
            setState { currentState.copy(isLoading = true) }
            networkRepository.getProduct(barcode).collect { dataState ->
                when (dataState) {
                    is DataState.Error -> {
                        setState { currentState.copy(isLoading = false) }
                        setEvent(ProductViewEvent.SnackBarError(dataState.apiError?.message))
                    }

                    is DataState.Loading -> {
                        setState { currentState.copy(isLoading = true) }
                    }

                    is DataState.Success -> {
                        setState {
                            currentState.copy(
                                isLoading = false,
                                data = listOf(dataState.data)
                            )
                        }
                    }
                }
            }
        }
    }

    fun startScanning() {
        viewModelScope.launch {
            setState { currentState.copy(isLoading = true) }
            scannerRepository.startScanning().collect { code ->
                if (!code.isNullOrBlank()) {
                    getProductByBarcode(code)
                }
            }
        }
    }

    override fun createInitialState() = ProductViewState()

    override fun onTriggerEvent(event: ProductViewEvent) {
        viewModelScope.launch {
            when (event) {
                is ProductViewEvent.ScanProduct -> startScanning()
                is ProductViewEvent.SnackBarError -> {}
            }
        }
    }
}