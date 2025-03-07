package com.test.newsappjetpack.di

import android.app.Application
import android.content.Context
import com.test.newsappjetpack.data.db.dao.NewsDao
import com.test.newsappjetpack.data.remote.NewsApi
import com.test.newsappjetpack.data.repository.NewsRepositoryImpl
import com.test.newsappjetpack.data.repository.UserRepositoryImpl
import com.test.newsappjetpack.domain.repository.NewsRepository
import com.test.newsappjetpack.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepositoryImpl(
        application: Application
    ): UserRepository {
        return UserRepositoryImpl(application.applicationContext)
    }

    @Provides
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }


    @Singleton
    @Provides
    fun provideNewsRepositoryImpl(
        newsApi: NewsApi,
        newsDao: NewsDao,
        ioDispatcher: CoroutineDispatcher
    ): NewsRepository {
        return NewsRepositoryImpl(newsApi, newsDao, ioDispatcher)
    }

    @Singleton
    @Provides
    fun provideDispatcherIo(): CoroutineDispatcher {
        return Dispatchers.IO
    }

}