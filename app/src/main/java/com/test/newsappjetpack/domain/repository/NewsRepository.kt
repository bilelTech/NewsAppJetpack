package com.test.newsappjetpack.domain.repository

import androidx.paging.PagingData
import com.test.newsappjetpack.domain.models.News
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(): Flow<PagingData<News>>
}