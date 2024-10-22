package com.example.proteinscanner.presentation.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.proteinscanner.R
import com.example.proteinscanner.data.util.toProduct
import com.example.proteinscanner.domain.model.Product

@Preview(showBackground = true)
@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val viewState = viewModel.uiState.collectAsState().value

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            ScannerButton(
                onScanClick = {
                    viewModel.startScanning()
                }
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { padding ->

        ScannedProducts(
            products = viewState.data?.map {
                it.toProduct()
            }.orEmpty(),
            paddings = padding
        )
    }
}

@Composable
fun ScannerButton(onScanClick: () -> Unit) {
    ExtendedFloatingActionButton(
        onClick = onScanClick,
        shape = FloatingActionButtonDefaults.extendedFabShape
    ) {
        Text(text = "Scan Product")
    }
}

@Composable
fun ScannedProducts(products: List<Product>, paddings: PaddingValues) {
    LazyColumn(modifier = Modifier.padding(paddings), contentPadding = paddings) {
        items(
            items = products,
            key = { it.id }
        ) { product ->
            ProductDetails(
                productName = product.name,
                productImage = product.image,
                productDescription = product.id.toString()
            )
        }
    }
}

@Composable
fun ProductDetails(
    modifier: Modifier = Modifier,
    productName: String,
    productImage: String,
    productDescription: String
) {
    Column {
        Text(text = productName)
        Text(text = productDescription)
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null
        )
    }
}
