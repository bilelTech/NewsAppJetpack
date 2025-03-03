package com.test.newsappjetpack.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.test.newsappjetpack.data.constants.Constants
import com.test.newsappjetpack.data.pagination.NewsPagingSource
import com.test.newsappjetpack.data.remote.NewsApi
import com.test.newsappjetpack.domain.models.News
import com.test.newsappjetpack.domain.repository.NewsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val backgroundDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : NewsRepository {

    /**
     * renvoie les actualités
     * par l'appel du web service
     */
    override fun getNews(): Flow<PagingData<News>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.MAX_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = {
                NewsPagingSource(newsApi)
            }
        ).flow.flowOn(backgroundDispatcher)
    }
}