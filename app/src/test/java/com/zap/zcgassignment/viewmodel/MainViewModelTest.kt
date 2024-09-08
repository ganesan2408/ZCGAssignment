package com.zap.zcgassignment.viewmodel

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.zap.zcgassignment.data.model.Block
import com.zap.zcgassignment.data.model.Product
import com.zap.zcgassignment.data.repository.BlockRepository
import com.zap.zcgassignment.ui.state.UIState
import com.zap.zcgassignment.utils.NetworkUtils
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()
    private val context: Context = mockk(relaxed = true)
    private val repository: BlockRepository = mockk(relaxed = true)
    private val networkUtils: NetworkUtils = mockk()
    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = MainViewModel(repository, context, networkUtils)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun `when there is no internet connection, uiState should be Error`() {
        every { networkUtils.isInternetAvailable(context) } returns false

        viewModel.loadBlocks()

        assertTrue(viewModel.uiState is UIState.Error)
        assertEquals("No internet connection. Please try again.", (viewModel.uiState as UIState.Error).message)
    }

    @Test
    fun `when data is fetched successfully, uiState should be Success`() {
        val blocks = listOf(
            Block(
                sectionType = "horizontal_scroll",
                items = listOf(Product(title = "Product 1", image = "image1.jpg"))
            ),
            Block(
                sectionType = "full_width_banner",
                items = null
            )
        )
        every { networkUtils.isInternetAvailable(context) } returns true
        coEvery { repository.getBlocks() } returns blocks

        viewModel.loadBlocks()

        assertTrue(viewModel.uiState is UIState.Success)
        assertEquals(blocks, (viewModel.uiState as UIState.Success).blocks)
    }

    @Test
    fun `when data fetch fails, uiState should be Error`() {
        every { networkUtils.isInternetAvailable(context) } returns true
        coEvery { repository.getBlocks() } throws Exception("Network error")

        viewModel.loadBlocks()

        assertTrue(viewModel.uiState is UIState.Error)
        assertEquals("Failed to load data: Network error", (viewModel.uiState as UIState.Error).message)
    }
}