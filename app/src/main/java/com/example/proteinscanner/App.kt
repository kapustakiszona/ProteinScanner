package com.example.proteinscanner

import android.app.Application
import com.example.proteinscanner.data.RepositoryImpl
import com.example.proteinscanner.domain.Repository
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning


class App : Application() {

    private val scanner by lazy {
        GmsBarcodeScanning.getClient(this)
    }
    val repository: Repository by lazy {
        RepositoryImpl(scanner)
    }
}