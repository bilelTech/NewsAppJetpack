package com.test.newsappjetpack.domain.usecases

import com.test.newsappjetpack.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    operator fun invoke() = newsRepository.getNews()
}