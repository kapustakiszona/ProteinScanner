package com.example.proteinscanner.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proteinscanner.domain.ScannerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val scannerRepository: ScannerRepository) : ViewModel() {

    private val _barcodeState = MutableStateFlow(BarcodeState())
    val barcodeState = _barcodeState.asStateFlow()

    fun startScanning() {
        viewModelScope.launch {
            scannerRepository.startScanning().collect { code ->
                if (!code.isNullOrBlank()){
                    _barcodeState.value = barcodeState.value.copy(
                        barcode = code
                    )
                }
            }
        }
    }
}