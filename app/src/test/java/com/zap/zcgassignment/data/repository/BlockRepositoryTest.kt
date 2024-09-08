package com.zap.zcgassignment.data.repository

import com.zap.zcgassignment.data.model.Block
import com.zap.zcgassignment.data.network.BlockApiService
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class BlockRepositoryTest {

    private lateinit var blockApi: BlockApiService
    private lateinit var blockRepository: BlockRepository

    @Before
    fun setUp() {
        blockApi = mockk()
        blockRepository = BlockRepository(blockApi)
    }

    @Test
    fun `getBlocks returns list of blocks from API`() = runBlocking {
        // Arrange
        val expectedBlocks = listOf(
            Block(sectionType = "horizontalFreeScroll", items = listOf()),
            Block(sectionType = "splitBanner", items = listOf())
        )
        val response = Response.success(expectedBlocks)
        coEvery { blockApi.fetchBlocks() } returns response

        // Act
        val actualBlocks = blockRepository.getBlocks()

        // Assert
        assertEquals(expectedBlocks, actualBlocks)
    }

    @Test
    fun `getBlocks returns null when API response is not successful`() = runBlocking {
        // Arrange
        val response = Response.error<List<Block>>(500, "".toResponseBody(null))
        coEvery { blockApi.fetchBlocks() } returns response

        // Act
        val actualBlocks = blockRepository.getBlocks()

        // Assert
        assertNull(actualBlocks)
    }

    @Test
    fun `getBlocks returns null when API response is empty`() = runBlocking {
        // Arrange
        val response = Response.success<List<Block>>(null)
        coEvery { blockApi.fetchBlocks() } returns response

        // Act
        val actualBlocks = blockRepository.getBlocks()

        // Assert
        assertNull(actualBlocks)
    }
}