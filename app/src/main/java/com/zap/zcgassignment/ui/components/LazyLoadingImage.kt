package com.zap.zcgassignment.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@Composable
fun LazyLoadingImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    Image(
        painter = rememberAsyncImagePainter(model = imageUrl),
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = ContentScale.Crop
    )

    /*val context = LocalContext.current

    val imagePainter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context)
            .data(imageUrl)
            .crossfade(500) // Set crossfade duration in milliseconds
            .placeholder(android.R.drawable.ic_menu_report_image)
            .error(android.R.drawable.ic_menu_close_clear_cancel)
            .build()
    )


    when (imagePainter.state) {
        is AsyncImagePainter.State.Loading -> {
            Box(
                modifier = modifier,
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(50.dp)
                )
            }
        }

        is AsyncImagePainter.State.Error -> {
            Icon(
                imageVector = Icons.Filled.Warning,
                contentDescription = "Image not available",
                modifier = Modifier.size(100.dp),
                tint = MaterialTheme.colorScheme.onBackground
            )
        }

        else -> {
            Image(
                painter = imagePainter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }*/
}

@Preview(showBackground = true)
@Composable
fun PreviewLazyLoadingImage() {
    LazyLoadingImage(
        imageUrl = "https://example.com/image.png", // Ensure this is a valid image URL
        modifier = Modifier.size(150.dp)
    )
}
