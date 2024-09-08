package com.zap.zcgassignment.data.repository

import com.zap.zcgassignment.data.model.Block
import com.zap.zcgassignment.data.network.BlockApiService
import javax.inject.Inject

class BlockRepository @Inject constructor(private val blockApiService: BlockApiService) {
    suspend fun getBlocks(): List<Block>? {
        val response = blockApiService.fetchBlocks()
        return if (response.isSuccessful) response.body() else null
    }
}