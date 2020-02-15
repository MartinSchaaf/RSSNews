package com.example.rssnews.model

import android.content.Context
import android.content.SharedPreferences

object UserSharedPreferences {

    private var sharedPreferences: SharedPreferences

    init {

        sharedPreferences = getSharedPreferences()
    }

    private const val userSharedPreferences:String = "UserSharedPreferences"
    private const val key:String = "category"

    private fun getSharedPreferences():SharedPreferences {

        val context = App.appCtx as Context

        return context.getSharedPreferences(userSharedPreferences, Context.MODE_PRIVATE)
    }

    fun setLastUserSelectedCategory(category: String){

        sharedPreferences.edit().putString(key,category).apply()
    }

    fun getLastUserSelectedCategory():String {

        return sharedPreferences.getString(key,"Все")!!
    }
}