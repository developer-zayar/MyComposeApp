package com.zayar.marsphotos.fake

import com.zayar.marsphotos.data.MarsPhotosRepository
import com.zayar.marsphotos.model.MarsPhoto

class FakeNetworkMarsPhotosRepository : MarsPhotosRepository {
    override suspend fun getMarsPhotos(): List<MarsPhoto> {
        return FakeDataSource.photosList
    }

}