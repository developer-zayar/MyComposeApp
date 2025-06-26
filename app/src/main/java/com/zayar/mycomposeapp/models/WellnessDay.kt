package com.zayar.mycomposeapp.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class WellnessDay(
    val day: Int, // The day number (e.g., 1, 2, 3...)
    @StringRes val titleResId: Int, // Resource ID for the title string
    @StringRes val descriptionResId: Int, // Resource ID for the details/description string
    @DrawableRes val imageResId: Int? = null, // Optional resource ID for the image drawable
    val imageUrl: String? = null // Optional URL for the image, if not using a drawable resource
)
