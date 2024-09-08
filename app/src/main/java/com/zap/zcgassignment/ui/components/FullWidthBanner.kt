package com.zap.zcgassignment.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FullWidthBanner(imageUrl: String) {
    LazyLoadingImage(
        imageUrl = imageUrl,
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .padding(8.dp),
    )
}
