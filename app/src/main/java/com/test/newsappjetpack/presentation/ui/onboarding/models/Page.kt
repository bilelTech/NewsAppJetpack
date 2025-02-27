package com.test.newsappjetpack.presentation.ui.onboarding.models

import androidx.annotation.DrawableRes

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)