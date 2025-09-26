package com.zayar.bookshelf.ui.screens

import com.zayar.bookshelf.model.Book

sealed class BookUiState {
    object loading : BookUiState()
    data class Success(val books: List<Book>) : BookUiState()
    data class Error(val message: String) : BookUiState()
}

sealed class BookDetailsUiState {
    object loading : BookDetailsUiState()
    data class Success(val book: Book?) : BookDetailsUiState()
    data class Error(val message: String) : BookDetailsUiState()
}