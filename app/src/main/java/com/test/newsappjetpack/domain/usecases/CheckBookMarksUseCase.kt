package com.test.newsappjetpack.domain.usecases

import com.test.newsappjetpack.domain.repository.NewsRepository
import javax.inject.Inject

class CheckBookMarksUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    operator fun invoke(url : String) = newsRepository.checkBookMarks(url)
}