package com.zayar.marsphotos

import com.zayar.marsphotos.fake.FakeDataSource
import com.zayar.marsphotos.fake.FakeNetworkMarsPhotosRepository
import com.zayar.marsphotos.rules.TestDispatcherRule
import com.zayar.marsphotos.ui.screens.MarsUiState
import com.zayar.marsphotos.ui.screens.MarsViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class MarsViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun marsViewModel_getMarsPhotos_verifyMarsUiStateSuccess() = runTest {
        val marsViewModel = MarsViewModel(
            marsPhotosRepository = FakeNetworkMarsPhotosRepository()
        )

        assertEquals(
            MarsUiState.Success(FakeDataSource.photosList),
            marsViewModel.marsUiState
        )
    }

}