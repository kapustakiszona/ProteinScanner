package com.example.proteinscanner.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proteinscanner.domain.ScannerRepository

class MainViewModelFactory(private val scannerRepository: ScannerRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(scannerRepository) as T
    }
}