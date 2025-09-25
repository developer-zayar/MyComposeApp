package com.zayar.bookshelf.model


import com.google.gson.annotations.SerializedName

data class Epub(
    @SerializedName("isAvailable")
    val isAvailable: Boolean?
)