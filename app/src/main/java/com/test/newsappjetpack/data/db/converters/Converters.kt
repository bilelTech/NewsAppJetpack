package com.test.newsappjetpack.data.db.converters

import com.test.newsappjetpack.data.db.entity.NewsEntity
import com.test.newsappjetpack.data.db.entity.SourceEntity
import com.test.newsappjetpack.data.utils.Utils
import com.test.newsappjetpack.domain.models.News
import com.test.newsappjetpack.presentation.models.NewsUI
import com.test.newsappjetpack.presentation.models.Source

fun Source.SourceUIToSourceEntity() = SourceEntity(id = id, name = name)

fun NewsEntity.NewsEntityToNews() = News(
    author = author ?: "",
    content = content ?: "",
    description = description ?: "",
    publishedAt = Utils.convertStringToLocalDateTime(this.publishedAt),
    source = com.test.newsappjetpack.domain.models.Source(id = source?.id ?: "", name = source?.name ?: ""),
    title = title ?: "",
    url = url,
    urlToImage = urlToImage ?: ""
)

fun NewsUI.NewsToNewsEntity() = NewsEntity(
    author = author,
    content = content,
    description = description,
    publishedAt = publishedAt,
    source = Source(id = source.id, name = source.name).SourceUIToSourceEntity(),
    title = title,
    url = url,
    urlToImage = urlToImage
)

fun NewsUI.NewsUIToNewsEntity() = NewsEntity(
    author = author,
    content = content,
    description = description,
    publishedAt = publishedAt,
    source = SourceEntity(id = source.id, name = source.name),
    title = title,
    url = url,
    urlToImage = urlToImage
)