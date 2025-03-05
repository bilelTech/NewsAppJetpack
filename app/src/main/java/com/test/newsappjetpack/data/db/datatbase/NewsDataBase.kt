package com.test.newsappjetpack.data.db.datatbase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.test.newsappjetpack.data.db.converters.NewsTypeConverter
import com.test.newsappjetpack.data.db.dao.NewsDao
import com.test.newsappjetpack.data.db.entity.NewsEntity

@Database(version = 1, entities = [NewsEntity::class])
@TypeConverters(NewsTypeConverter::class)
abstract class NewsDataBase: RoomDatabase() {

    abstract fun newsDao(): NewsDao
    companion object {
        val DATABASE_NAME: String = "news_db"
    }
}