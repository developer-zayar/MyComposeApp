package com.zayar.inventory.ui.home

import androidx.lifecycle.ViewModel
import com.zayar.inventory.model.Item

class HomeViewModel : ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

/**
 * Ui State for HomeScreen
 */
data class HomeUiState(val itemList: List<Item> = listOf())