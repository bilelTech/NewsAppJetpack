package com.test.newsappjetpack.domain.models

import java.time.LocalDateTime

data class News(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: LocalDateTime?,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)