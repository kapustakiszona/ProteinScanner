package com.example.proteinscanner.domain

import kotlinx.coroutines.flow.Flow

interface Repository {
    fun startScanning(): Flow<String?>
}