package com.test.newsappjetpack.presentation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsUI(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String?,
    val title: String,
    val source: Source,
    val url: String,
    val urlToImage: String
): Parcelable