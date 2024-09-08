package com.zap.zcgassignment.ui.components

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.zap.zcgassignment.data.model.Product

@Composable
fun HorizontalProductList(products: List<Product>) {
    LazyRow {
        items(products) { product ->
            ProductCard(product)
        }
    }
}