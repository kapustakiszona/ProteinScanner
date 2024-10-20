package com.example.proteinscanner.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proteinscanner.data.ScannerRepositoryImpl
import com.example.proteinscanner.data.network.api.NetworkRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor
    (
    private val scannerRepository: ScannerRepositoryImpl,
    private val networkRepository: NetworkRepositoryImpl
) : ViewModel() {

    private val _barcodeState = MutableStateFlow(BarcodeState())
    val barcodeState = _barcodeState.asStateFlow()

    fun startScanning() {
        viewModelScope.launch {
            scannerRepository.startScanning().collect { code ->
                if (!code.isNullOrBlank()) {
                    _barcodeState.value = barcodeState.value.copy(
                        barcode = code
                    )
                }
            }
        }
    }
}