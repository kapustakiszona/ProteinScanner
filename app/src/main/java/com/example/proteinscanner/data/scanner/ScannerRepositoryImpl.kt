package com.example.proteinscanner.data.scanner

import com.example.proteinscanner.domain.repository.ScannerRepository
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ScannerRepositoryImpl @Inject constructor(private val scanner: GmsBarcodeScanner) :
    ScannerRepository {
    override fun startScanning(): Flow<String?> {
        return callbackFlow {
            scanner.startScan()
                .addOnSuccessListener {
                    launch {
                        send(getDetails(it))
                    }
                }
                .addOnFailureListener {
                    it.printStackTrace()
                }
            awaitClose { }
        }
    }

    fun getDetails(barcode: Barcode): String {
        return if (barcode.valueType == Barcode.TYPE_PRODUCT) {
            "${barcode.displayValue}"
        } else {
            "I can't scan that type of barcode"
        }
    }
}