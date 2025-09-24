package com.zayar.amphibians.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.zayar.amphibians.AmpApplication
import com.zayar.amphibians.data.AmpRepository
import kotlinx.coroutines.launch

class AmpViewModel(
    private val ampRepository: AmpRepository
) : ViewModel() {

    var ampUiState: AmpUiState by mutableStateOf(AmpUiState.Loading)
        private set

    init {
        getAmphibians()
    }

    fun getAmphibians() {
        viewModelScope.launch {
            ampUiState = AmpUiState.Loading
            ampUiState = try {
                val listResult = ampRepository.getAmphibians()
                AmpUiState.Success(listResult)
            } catch (e: Exception) {
                AmpUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmpApplication)
                val ampRepository = application.container.ampRepository
                AmpViewModel(ampRepository = ampRepository)
            }
        }
    }
}