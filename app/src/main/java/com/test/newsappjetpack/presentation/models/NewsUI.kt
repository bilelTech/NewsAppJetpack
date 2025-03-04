package com.test.newsappjetpack.presentation.models

data class NewsUI(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String?,
    val title: String,
    val url: String,
    val urlToImage: String
)