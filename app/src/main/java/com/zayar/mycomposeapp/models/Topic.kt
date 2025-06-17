package com.zayar.mycomposeapp.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val title: Int,
    val numberOfCourses: Int,
    @DrawableRes val imageResourceId: Int,
)
