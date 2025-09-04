package com.zayar.mycity.model

data class CityUiState(
    val categories: List<Category> = emptyList(),
    val recommendations: List<Recommendation> = emptyList(),
    val placeDetails: PlaceDetails? = null,
    val currentCategory: Category? = null,
    val currentRecommendation: Recommendation? = null
)
