package com.zayar.mycity.model

data class Category(
    val id: Int,
    val name: String,
    val imageUrl: String
)

data class Recommendation(
    val id: Int,
    val categoryId: Int,
    val name: String,
    val imageUrl: String
)

data class PlaceDetails(
    val recommendationId: Int,
    val title: String,
    val description: String,
    val imageUrl: String
)
