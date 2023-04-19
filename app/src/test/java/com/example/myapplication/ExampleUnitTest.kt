package com.example.myapplication

import com.example.myapplication.scans.model.ScanItem
import com.example.myapplication.scans.model.ScansRepositoryImpl
import com.example.myapplication.scans.model.ScansUseCase
import com.example.myapplication.scans.viewModel.ScansViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.stub

class ExampleUnitTest {

    @InjectMocks
    lateinit var scansUseCase: ScansUseCase

    @Mock
    lateinit var repository: ScansRepositoryImpl

    val dispatcher = TestCoroutineDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun setScansToState_WHEN_repoReturnScansWithSucces() = runTest {

        val scansToReturn = getMockItems()

        repository.stub {
            onBlocking { getScans() }.doReturn(scansToReturn)
        }

        val scansViewModel = ScansViewModel(scansUseCase)
        assertEquals(scansToReturn, scansViewModel.state.value.scans.value)
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun setErrorToState_WHEN_repoReturnScansWithError() = runTest {

        repository.stub {
            onBlocking { getScans() }.doThrow(Exception(ERROR_MSG))
        }

        val scansViewModel = ScansViewModel(scansUseCase)
        assertEquals(ERROR_MSG, scansViewModel.state.value.error)
    }

    private fun getMockItems(): MutableList<ScanItem> {
        val items = (1..10).map {
            ScanItem(
                it
            )
        }
        return items.toMutableList()
    }

    companion object {
        private const val ERROR_MSG = "not valid"
    }
}