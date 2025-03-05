package com.test.newsappjetpack.di

import android.content.Context
import androidx.room.Room
import com.test.newsappjetpack.data.db.dao.NewsDao
import com.test.newsappjetpack.data.db.datatbase.NewsDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context): NewsDataBase {
        return Room
            .databaseBuilder(context, NewsDataBase::class.java, NewsDataBase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun provideNewsDao(db: NewsDataBase): NewsDao {
        return db.newsDao()
    }
}