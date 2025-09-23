package com.zayar.marsphotos.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.zayar.marsphotos.MarsPhotosApplication
import com.zayar.marsphotos.data.MarsPhotosRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MarsViewModel(
    private val marsPhotosRepository: MarsPhotosRepository
) : ViewModel() {
//    var marsUiState: String by mutableStateOf("")
//        private set

    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
        private set

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MarsPhotosApplication)
                val marsPhotosRepository = application.container.marsRepository
                MarsViewModel(marsPhotosRepository = marsPhotosRepository)
            }
        }
    }

    init {
        getMarsPhotos()
    }

    fun getMarsPhotos() {
//        marsUiState = "Set the Mars API status response here!"
        viewModelScope.launch {
            marsUiState = MarsUiState.Loading
            marsUiState = try {
//                val listResult = MarsApi.retrofitService.getPhotos()
//                marsUiState = listResult
                val listResult = marsPhotosRepository.getMarsPhotos()
//                MarsUiState.Success("Success: ${listResult.size} Mars photos retrieved")
//                Log.d("MarsViewModel", "getMarsPhotos: $listResult")
                MarsUiState.Success(listResult)
            } catch (e: IOException) {
                MarsUiState.Error
            } catch (e: HttpException) {
                MarsUiState.Error
            }
        }
    }
}