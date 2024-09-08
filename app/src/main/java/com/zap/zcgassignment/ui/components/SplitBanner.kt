package com.zap.zcgassignment.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SplitBanner(imageUrl1: String, imageUrl2: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .padding(8.dp)
    ) {
        LazyLoadingImage(
            imageUrl = imageUrl1,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(end = 4.dp),
        )
        LazyLoadingImage(
            imageUrl = imageUrl2,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(start = 4.dp),
        )
    }
}
