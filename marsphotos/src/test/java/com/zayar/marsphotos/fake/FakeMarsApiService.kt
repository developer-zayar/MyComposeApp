package com.zayar.marsphotos.fake

import com.zayar.marsphotos.model.MarsPhoto
import com.zayar.marsphotos.network.MarsApiService

class FakeMarsApiService : MarsApiService {
    override suspend fun getPhotos(): List<MarsPhoto> {
        return FakeDataSource.photosList
    }
}