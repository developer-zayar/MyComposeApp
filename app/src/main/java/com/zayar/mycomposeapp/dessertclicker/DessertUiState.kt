package com.zayar.mycomposeapp.dessertclicker

import androidx.annotation.DrawableRes
import com.zayar.mycomposeapp.data.Datasource

data class DessertUiState(
    val revenue: Int = 0,
    val dessertsSold: Int = 0,
    val currentDessertIndex: Int = 0,
    val currentDessertPrice: Int = Datasource.dessertList[currentDessertIndex].price,
    @DrawableRes val currentDessertImageId: Int = Datasource.dessertList[currentDessertIndex].imageId
)
