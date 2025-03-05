package com.test.newsappjetpack.data.converters

import com.test.newsappjetpack.data.models.ArticleDto
import com.test.newsappjetpack.data.models.Source
import com.test.newsappjetpack.data.utils.Utils
import com.test.newsappjetpack.domain.models.News

fun ArticleDto.mapFromDto() = News(
    author = this.author.orEmpty(),
    content = this.content.orEmpty(),
    description = this.description.orEmpty(),
    publishedAt = Utils.convertStringToLocalDateTime(this.publishedAt),
    title = this.title.orEmpty(),
    source = this.source.mapSourceFromDto(),
    url = this.url.orEmpty(),
    urlToImage = this.urlToImage.orEmpty(),
)


fun Source.mapSourceFromDto() = com.test.newsappjetpack.domain.models.Source(id = this.id ?: "", name = this.name ?: "")
fun List<ArticleDto>.mapFromListModel(): List<News> {
    return this.map {
        it.mapFromDto()
    }
}