package com.zayar.mycity.ui

import androidx.lifecycle.ViewModel
import com.zayar.mycity.data.LocalDataProvider
import com.zayar.mycity.model.CityUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CityViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CityUiState())
    val uiState: StateFlow<CityUiState> = _uiState.asStateFlow()

    fun getCategories() = LocalDataProvider.categories

    fun getRecommendationsByCategory(categoryId: Int) =
        LocalDataProvider.recommendations.filter { it.categoryId == categoryId }

    fun getPlaceDetails(recommendationId: Int) =
        LocalDataProvider.placeDetails.first { it.recommendationId == recommendationId }
}