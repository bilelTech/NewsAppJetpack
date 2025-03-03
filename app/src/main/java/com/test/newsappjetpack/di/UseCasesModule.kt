package com.test.newsappjetpack.di

import com.test.newsappjetpack.domain.repository.NewsRepository
import com.test.newsappjetpack.domain.repository.UserRepository
import com.test.newsappjetpack.domain.usecases.AppEntryUseCases
import com.test.newsappjetpack.domain.usecases.GetNewsUseCase
import com.test.newsappjetpack.domain.usecases.ReadAppEntryUseCase
import com.test.newsappjetpack.domain.usecases.SaveAppEntryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class UseCasesModule {

    @Singleton
    @Provides
    fun provideSaveAppEntryUseCase(userRepository: UserRepository) =
        SaveAppEntryUseCase(userRepository)


    @Singleton
    @Provides
    fun provideReadAppEntryUseCase(userRepository: UserRepository) =
        ReadAppEntryUseCase(userRepository)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        userRepository: UserRepository
    ): AppEntryUseCases = AppEntryUseCases(
        readAppEntryUseCase = ReadAppEntryUseCase(userRepository),
        saveAppEntryUseCase = SaveAppEntryUseCase(userRepository)
    )

    @Singleton
    @Provides
    fun provideGetNewsUseCase(
        newsRepository: NewsRepository
    ): GetNewsUseCase = GetNewsUseCase(
        newsRepository = newsRepository
    )
}