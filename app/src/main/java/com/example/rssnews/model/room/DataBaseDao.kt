package com.example.rssnews.model.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.rssnews.model.pojo.Rss

@Dao
interface DataBaseDao {

    @Query("SELECT * FROM response")
    suspend fun getAll(): Rss

    @Insert
    suspend fun insert(response: Rss)
}