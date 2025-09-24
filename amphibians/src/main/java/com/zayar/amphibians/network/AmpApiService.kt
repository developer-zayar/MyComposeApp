package com.zayar.amphibians.network

import com.zayar.amphibians.model.Amphibian
import retrofit2.http.GET

interface AmpApiService {
    @GET("amphibians")
    suspend fun getAmphibians(): List<Amphibian>
}