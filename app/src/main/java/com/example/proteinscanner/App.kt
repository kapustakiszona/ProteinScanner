package com.example.proteinscanner

import android.app.Application
import com.example.proteinscanner.data.ScannerRepositoryImpl
import com.example.proteinscanner.domain.ScannerRepository
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning


class App : Application() {

    private val scanner by lazy {
        GmsBarcodeScanning.getClient(this)
    }
    val repository: ScannerRepository by lazy {
        ScannerRepositoryImpl(scanner)
    }
}