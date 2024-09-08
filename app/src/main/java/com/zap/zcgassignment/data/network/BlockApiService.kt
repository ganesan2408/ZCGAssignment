package com.zap.zcgassignment.data.network

import com.zap.zcgassignment.data.model.Block
import retrofit2.Response
import retrofit2.http.GET

interface BlockApiService {
    @GET("/b/5BEJ")
    suspend fun fetchBlocks(): Response<List<Block>>
}
