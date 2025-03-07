package com.test.newsappjetpack.domain.usecases

import com.test.newsappjetpack.domain.models.News
import com.test.newsappjetpack.domain.repository.NewsRepository
import javax.inject.Inject

class AddBookMarksUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    operator fun invoke(news: News) = newsRepository.addBookMarks(news)
}