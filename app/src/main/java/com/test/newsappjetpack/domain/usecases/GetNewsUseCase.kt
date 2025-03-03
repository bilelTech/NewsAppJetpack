package com.test.newsappjetpack.domain.usecases

import com.test.newsappjetpack.domain.repository.NewsRepository

class GetNewsUseCase(private val newsRepository: NewsRepository) {
    operator fun invoke() = newsRepository.getNews()
}