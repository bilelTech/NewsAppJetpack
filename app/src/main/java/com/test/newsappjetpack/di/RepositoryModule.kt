package com.test.newsappjetpack.di

import android.app.Application
import android.content.Context
import com.test.newsappjetpack.data.repository.UserRepositoryImpl
import com.test.newsappjetpack.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
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
}