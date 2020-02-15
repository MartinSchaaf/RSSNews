package com.example.rssnews.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_items")
data class DataBaseNewsItem (

    var imageUrl:String,
    var title:String,
    var description:String,
    var fullText:String,
    var pubDate:String
){
    @PrimaryKey(autoGenerate = true)
    var Id:Int = 0
}