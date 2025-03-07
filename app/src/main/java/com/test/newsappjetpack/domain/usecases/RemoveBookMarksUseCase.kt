package com.test.newsappjetpack.domain.usecases

import com.test.newsappjetpack.domain.models.News
import com.test.newsappjetpack.domain.repository.NewsRepository
import javax.inject.Inject

class RemoveBookMarksUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(news: News) = newsRepository.removeBookMarks(news.url)
}