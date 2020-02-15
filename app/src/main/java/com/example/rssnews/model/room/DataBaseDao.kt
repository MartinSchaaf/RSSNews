package com.example.rssnews.model.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DataBaseDao {

    @Query("SELECT * FROM news_items")
    suspend fun getAll(): List<NewsItem>?

    @Insert
    suspend fun insert(item: NewsItem)

    @Delete
    suspend fun delete(item: NewsItem)
}