package com.example.proteinscanner.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proteinscanner.domain.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {

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
}