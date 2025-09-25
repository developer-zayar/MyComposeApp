package com.zayar.bookshelf.network

import com.zayar.bookshelf.model.BookResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BookshelfApiService {
    @GET("volumes")
    suspend fun searchBooks(
        @Query("q") query: String
    ): BookResponse
}