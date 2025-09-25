package com.zayar.bookshelf.model


import com.google.gson.annotations.SerializedName

data class ReadingModes(
    @SerializedName("text")
    val text: Boolean?,
    @SerializedName("image")
    val image: Boolean?
)