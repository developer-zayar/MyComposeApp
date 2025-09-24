package com.zayar.amphibians.data

import com.zayar.amphibians.model.Amphibian
import com.zayar.amphibians.network.AmpApiService

interface AmpRepository {
    suspend fun getAmphibians(): List<Amphibian>
}

class AmpRepositoryImpl(
    private val apiService: AmpApiService
) : AmpRepository {

    override suspend fun getAmphibians(): List<Amphibian> {
        return apiService.getAmphibians()
    }

}