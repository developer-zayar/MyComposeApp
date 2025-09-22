package com.zayar.marsphotos.data

import com.zayar.marsphotos.model.MarsPhoto
import com.zayar.marsphotos.network.MarsApiService

interface MarsPhotosRepository {
    suspend fun getMarsPhotos(): List<MarsPhoto>
}

class NetworkMarsRepository(
    private val marsApiService: MarsApiService
) : MarsPhotosRepository {

    override suspend fun getMarsPhotos(): List<MarsPhoto> {
        return marsApiService.getPhotos()
    }

}