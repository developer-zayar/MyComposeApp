package com.zayar.marsphotos.fake

import com.zayar.marsphotos.data.NetworkMarsRepository
import org.junit.Assert.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class NetworkMarsRepositoryTest {

    @Test
    fun networkMarsPhotosRepository_getMarsPhotos_verifyPhotoList() = runTest {
        val repository = NetworkMarsRepository(
            marsApiService = FakeMarsApiService()
        )

        assertEquals(FakeDataSource.photosList, repository.getMarsPhotos())
    }

}