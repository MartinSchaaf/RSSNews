package com.example.rssnews.model.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rssnews.model.pojo.Rss

@Database(entities = [Rss::class],version = 1)
abstract class Database : RoomDatabase() {

    abstract fun getDao():DataBaseDao
}