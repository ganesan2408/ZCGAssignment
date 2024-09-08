package com.zap.zcgassignment.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zap.zcgassignment.data.repository.BlockRepository
import com.zap.zcgassignment.ui.state.UIState
import com.zap.zcgassignment.utils.NetworkUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: BlockRepository,
    @ApplicationContext private val context: Context,
    private val networkUtils: NetworkUtils
) : ViewModel() {
    var uiState by mutableStateOf<UIState>(UIState.Loading)
        private set

//    init {
//        loadBlocks()
//    }

    fun loadBlocks() {
        if (!networkUtils.isInternetAvailable(context)) {
            uiState = UIState.Error("No internet connection. Please try again.")
            return
        }
        viewModelScope.launch {
            try {
                val blocks = repository.getBlocks()
                uiState = blocks?.let { UIState.Success(it) } ?: UIState.Error("No Data")
            } catch (e: Exception) {
                uiState = UIState.Error("Failed to load data: ${e.message}")
            }
        }
    }
}
