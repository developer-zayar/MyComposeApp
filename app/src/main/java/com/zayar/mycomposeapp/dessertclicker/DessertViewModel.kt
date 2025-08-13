package com.zayar.mycomposeapp.dessertclicker

import androidx.lifecycle.ViewModel
import com.zayar.mycomposeapp.data.Datasource
import com.zayar.mycomposeapp.models.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel : ViewModel() {

    private val _dessertUiState = MutableStateFlow(DessertUiState())
    val dessertUiState: StateFlow<DessertUiState> = _dessertUiState.asStateFlow()


    fun onDessertClicked() {
        _dessertUiState.update { currentState ->
            val dessertSold = currentState.dessertsSold.plus(1)
            val nextDessertIndex = determineDessertToShow(dessertSold)
            currentState.copy(
                currentDessertIndex = nextDessertIndex,
                revenue = currentState.revenue + currentState.currentDessertPrice,
                dessertsSold = dessertSold,
                currentDessertImageId = Datasource.dessertList[nextDessertIndex].imageId,
                currentDessertPrice = Datasource.dessertList[nextDessertIndex].price
            )
        }
    }

    private fun determineDessertToShow(
        dessertsSold: Int
    ): Int {
        var dessertIndex = 0
        for (index in Datasource.dessertList.indices) {
            if (dessertsSold >= Datasource.dessertList[index].startProductionAmount) {
                dessertIndex = index
            } else {
                break
            }
        }

        return dessertIndex
    }

}