package com.example.proteinscanner.presentation.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.proteinscanner.R

@Preview(showBackground = true)
@Composable
fun MainScreen() {
    val productList = remember { mutableStateListOf<Product>() }
    var nextProductId: Long = 4
    Scaffold(
        floatingActionButton = {
            ScannerButton(
                onScanClick = {
                    val newProduct = Product(
                        name = "New Product $nextProductId",
                        description = "Description for new product",
                        image = "new_product_image.jpg",
                        id = nextProductId
                    )
                    productList.add(newProduct)
                    nextProductId++
                }
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { padding ->
       ScannedProducts(paddings = padding, products = productList)
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
                productDescription = product.description
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
