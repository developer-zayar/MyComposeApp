package com.zayar.amphibians.ui.screens

import com.zayar.amphibians.model.Amphibian

sealed interface AmpUiState {
    data class Success(val amphibians: List<Amphibian>) : AmpUiState
    object Error : AmpUiState
    object Loading : AmpUiState
}