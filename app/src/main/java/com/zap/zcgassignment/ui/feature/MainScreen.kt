package com.zap.zcgassignment.ui.feature

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zap.zcgassignment.ui.components.FullWidthBanner
import com.zap.zcgassignment.ui.components.HorizontalProductList
import com.zap.zcgassignment.ui.components.SplitBanner
import com.zap.zcgassignment.viewmodel.MainViewModel
import com.zap.zcgassignment.ui.state.UIState

@Composable
fun MainScreen(viewModel: MainViewModel) {
    when (val state = viewModel.uiState) {
        is UIState.Loading -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(modifier = Modifier.wrapContentSize())
            }
        }

        is UIState.Success -> {
            LazyColumn {
                items(state.blocks) { block ->
                    when (block.sectionType) {
                        "horizontalFreeScroll" -> {
                            HorizontalProductList(products = block.items ?: emptyList())
                        }

                        "splitBanner" -> {
                            SplitBanner(
                                imageUrl1 = block.items?.get(0)?.image ?: "",
                                imageUrl2 = block.items?.get(1)?.image ?: ""
                            )
                        }

                        "banner" -> {
                            FullWidthBanner(imageUrl = block.items?.get(0)?.image ?: "")
                        }
                    }
                }
            }
        }

        is UIState.Error -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = state.message)
                Button(
                    modifier = Modifier.padding(top = 15.dp),
                    onClick = { viewModel.loadBlocks() }) {
                    Text("Retry")
                }
            }
        }
    }
}
