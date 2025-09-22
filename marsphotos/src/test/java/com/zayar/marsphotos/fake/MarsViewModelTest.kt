package com.zayar.marsphotos.fake

import com.zayar.marsphotos.ui.screens.MarsUiState
import com.zayar.marsphotos.ui.screens.MarsViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class MarsViewModelTest {

    @Test
    fun marsViewModel_getMarsPhotos_verifyMarsUiStateSuccess() = runTest {
        val marsViewModel = MarsViewModel(
            marsPhotosRepository = FakeNetworkMarsPhotosRepository()
        )

        assertEquals(
            MarsUiState.Success("Success: ${FakeDataSource.photosList.size} Mars photos retrieved"),
            marsViewModel.marsUiState
        )
    }

}