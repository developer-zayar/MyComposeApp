package com.zayar.bookshelf.network


import com.google.gson.annotations.SerializedName
import com.zayar.bookshelf.model.Book

data class BookResponse(
    @SerializedName("kind")
    val kind: String?,
    @SerializedName("totalItems")
    val totalItems: Int?,
    @SerializedName("items")
    val items: List<Book>?
)