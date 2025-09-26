package com.zayar.bookshelf.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.zayar.bookshelf.BookshelfApplication
import com.zayar.bookshelf.data.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BookViewModel(
    private val bookRepository: BookRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<BookUiState>(BookUiState.loading)
    val uiState: StateFlow<BookUiState> = _uiState.asStateFlow()

    private val _bookDetailsUiState = MutableStateFlow<BookDetailsUiState>(BookDetailsUiState.loading)
    val bookDetailsUiState: StateFlow<BookDetailsUiState> = _bookDetailsUiState.asStateFlow()

    init {
        searchBooks("jazz+history")
    }

    fun searchBooks(query: String) {
        viewModelScope.launch {
            _uiState.value = BookUiState.loading
            try {
                val response = bookRepository.searchBooks(query)
                _uiState.value = BookUiState.Success(response.items ?: emptyList())
            } catch (e: Exception) {
                _uiState.value = BookUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun getBookDetails(volumeId: String) {
        viewModelScope.launch {
            _bookDetailsUiState.value = BookDetailsUiState.loading
            try {
                val response = bookRepository.getBookDetails(volumeId)
                _bookDetailsUiState.value = BookDetailsUiState.Success(response)
            } catch (e: Exception) {
                _bookDetailsUiState.value = BookDetailsUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BookshelfApplication)
                val bookRepository = application.container.bookRepository
                BookViewModel(bookRepository = bookRepository)
            }
        }
    }
}