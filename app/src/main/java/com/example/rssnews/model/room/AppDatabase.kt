package com.example.rssnews.model.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NewsItem::class],version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getDao():DataBaseDao
}