package com.zayar.marsphotos.ui.screens

import com.zayar.marsphotos.model.MarsPhoto

sealed interface MarsUiState {
    data class Success(val photos: List<MarsPhoto>) : MarsUiState
    object Error : MarsUiState
    object Loading : MarsUiState
}