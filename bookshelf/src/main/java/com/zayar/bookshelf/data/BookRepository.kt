package com.zayar.bookshelf.data

import com.zayar.bookshelf.model.Book
import com.zayar.bookshelf.network.BookResponse
import com.zayar.bookshelf.network.BookshelfApiService

interface BookRepository {
    suspend fun searchBooks(query: String): BookResponse
    suspend fun getBookDetails(volumeId: String): Book?
}

class BookRepositoryImpl(
    private val apiService: BookshelfApiService
) : BookRepository {

    override suspend fun searchBooks(query: String): BookResponse {
        return apiService.searchBooks(query)
    }

    override suspend fun getBookDetails(volumeId: String): Book? {
        return apiService.getBookDetails(volumeId)
    }
}