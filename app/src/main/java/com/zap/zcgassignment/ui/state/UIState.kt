package com.zap.zcgassignment.ui.state

import com.zap.zcgassignment.data.model.Block

sealed class UIState {
    data object Loading : UIState()
    data class Success(val blocks: List<Block>) : UIState()
    data class Error(val message: String) : UIState()
}