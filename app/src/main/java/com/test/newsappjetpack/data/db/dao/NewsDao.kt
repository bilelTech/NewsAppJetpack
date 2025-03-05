package com.test.newsappjetpack.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.newsappjetpack.data.db.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    // insert news on database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: NewsEntity)

    // insert list of news on database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: List<NewsEntity>)

    // get list of news from database
    @Query("SELECT * FROM news")
    fun getNews(): Flow<List<NewsEntity>>


    @Query("SELECT * FROM news WHERE id=:id")
    fun getNewsById(id: Int): NewsEntity?

    @Query("DELETE FROM news")
    fun clear()
}