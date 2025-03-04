package com.test.newsappjetpack.data.converters

import com.test.newsappjetpack.data.models.ArticleDto
import com.test.newsappjetpack.data.utils.Utils
import com.test.newsappjetpack.domain.models.News

fun ArticleDto.mapFromEntity() = News(
    author = this.author.orEmpty(),
    content = this.content.orEmpty(),
    description = this.description.orEmpty(),
    publishedAt = Utils.convertStringToLocalDateTime(this.publishedAt),
    title = this.title.orEmpty(),
    url = this.url.orEmpty(),
    urlToImage = this.urlToImage.orEmpty(),
)
fun List<ArticleDto>.mapFromListModel(): List<News> {
    return this.map {
        it.mapFromEntity()
    }
}