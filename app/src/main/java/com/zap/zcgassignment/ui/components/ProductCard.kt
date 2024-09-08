package com.zap.zcgassignment.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zap.zcgassignment.data.model.Product

@Composable
fun ProductCard(product: Product) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .width(124.dp)
            .height(124.dp)
    ) {
        LazyLoadingImage(
            imageUrl = product.image,
            modifier = Modifier
                .size(124.dp)
                .clip(RoundedCornerShape(8.dp)),
        )
        Text(
            text = product.title,
            modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.BottomEnd)
                .background(
                    Color(0x80000000), RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
                )
                .padding(8.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = Color.White
        )
    }
}

@Composable
@Preview
fun previewProductCard() {
    ProductCard(
        product = Product(
            title = "Product Title", image = "https://example.com/product_image.jpg"
        )
    )
}