package com.example.rssnews.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_items")
data class NewsItem (

    var imageUrl:String,
    var title:String,
    var description:String,
    var fullText:String,
    var pubDate:String,
    var link:String,
    var category:String
){
    @PrimaryKey(autoGenerate = true)
    var Id:Int = 0
}