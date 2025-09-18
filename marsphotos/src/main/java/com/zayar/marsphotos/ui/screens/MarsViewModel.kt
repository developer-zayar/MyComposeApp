package com.zayar.marsphotos.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zayar.marsphotos.network.MarsApi
import kotlinx.coroutines.launch
import java.io.IOException

class MarsViewModel : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
//    var marsUiState: String by mutableStateOf("")
//        private set

    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
        private set

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getMarsPhotos()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [MutableList].
     */
    private fun getMarsPhotos() {
//        marsUiState = "Set the Mars API status response here!"
        viewModelScope.launch {
            try {
                val listResult = MarsApi.retrofitService.getPhotos()
//                marsUiState = listResult
                marsUiState = MarsUiState.Success(
                    "Success: ${listResult.size} Mars photos retrieved"
                )
            } catch (e: IOException) {
                marsUiState = MarsUiState.Error
            }
        }
    }
}