package com.test.newsappjetpack.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.test.newsappjetpack.data.constants.Constants
import com.test.newsappjetpack.data.db.converters.NewsEntityToNews
import com.test.newsappjetpack.data.db.dao.NewsDao
import com.test.newsappjetpack.data.db.entity.NewsEntity
import com.test.newsappjetpack.data.db.entity.SourceEntity
import com.test.newsappjetpack.data.pagination.NewsPagingSource
import com.test.newsappjetpack.data.remote.NewsApi
import com.test.newsappjetpack.domain.models.News
import com.test.newsappjetpack.domain.repository.NewsRepository
import com.test.newsappjetpack.utils.convertDateToString
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.invoke
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val newsDao: NewsDao,
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

    /**
     * ajouter un article aux favoris
     */
    override fun addBookMarks(news: News) {
        newsDao.insert(
            NewsEntity(
                author = news.author,
                content = news.content,
                description = news.description,
                publishedAt = news.publishedAt?.convertDateToString(),
                source = SourceEntity(id = news.source.id, name = news.source.name),
                title = news.title,
                url = news.url,
                urlToImage = news.urlToImage
            )
        )
    }


    /**
     * supprimer news des favoris
     */
    override suspend fun removeBookMarks(url: String) {
        backgroundDispatcher.invoke {
            newsDao.deleteNewsByUrl(url)
        }
    }

    /**
     * renvoie la liste des favoris
     */
    override fun getBookMarks(): Flow<List<News>> = flow {
        emit(newsDao.getNews().map { it.NewsEntityToNews() })
    }

    override fun checkBookMarks(url: String) = flow { emit(newsDao.getNewsByUrl(url) != null) }


}