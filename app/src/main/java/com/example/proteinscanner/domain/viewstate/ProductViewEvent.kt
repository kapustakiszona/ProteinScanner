package com.example.proteinscanner.domain.viewstate

sealed class ProductViewEvent : ViewEvent {
    class ScanProduct(val barcode: String) : ProductViewEvent()
    class SnackBarError(val message: String?) : ProductViewEvent()
}
