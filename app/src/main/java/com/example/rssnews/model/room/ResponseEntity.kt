package com.example.rssnews.model.room

import androidx.room.Embedded
import androidx.room.Entity
import com.example.rssnews.model.pojo.Rss



data class ResponseEntity(


    val response: Rss
)