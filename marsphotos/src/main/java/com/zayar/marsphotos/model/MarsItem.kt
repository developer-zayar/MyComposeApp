package com.zayar.marsphotos.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MarsItem(
    @SerialName("id")
    val id: String? = null,
    @SerialName("img_src")
    val imgSrc: String? = null
)