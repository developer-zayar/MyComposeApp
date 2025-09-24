package com.zayar.amphibians.data

import com.zayar.amphibians.network.AmpApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

interface AppContainer {
    val ampRepository: AmpRepository
}

class DefaultAppContainer : AppContainer {
    private val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json; charset=UTF8".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: AmpApiService by lazy {
        retrofit.create(AmpApiService::class.java)
    }

    override val ampRepository: AmpRepository by lazy {
        AmpRepositoryImpl(retrofitService)
    }

}