package com.test.newsappjetpack.utils

import com.test.newsappjetpack.domain.models.News
import com.test.newsappjetpack.presentation.models.NewsUI
import com.test.newsappjetpack.presentation.models.Source
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun News.mapToNewsUI() = NewsUI(
    author = this.author,
    content = this.content,
    description = this.description,
    publishedAt = this.publishedAt?.convertDateToString(),
    title = this.title,
    source = Source(id = this.source.id, name = this.source.name),
    url = this.url,
    urlToImage = this.urlToImage
)

fun LocalDateTime.convertDateToString(): String =
    this.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))