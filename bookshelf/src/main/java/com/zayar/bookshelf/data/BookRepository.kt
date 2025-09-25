package com.zayar.bookshelf.data

import com.zayar.bookshelf.model.Book
import com.zayar.bookshelf.network.BookshelfApiService

interface BookRepository {
    suspend fun searchBooks(query: String): List<Book>
}

class BookRepositoryImpl(
    private val apiService: BookshelfApiService
) : BookRepository {

    override suspend fun searchBooks(query: String): List<Book> {
        return apiService.searchBooks(query).items
    }

}