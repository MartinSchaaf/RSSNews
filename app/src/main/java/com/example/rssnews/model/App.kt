package com.example.rssnews.model

import android.app.Application

class App : Application(){

    override fun onCreate() {
        super.onCreate()
        appCtx = applicationContext
    }

    companion object{

        lateinit var appCtx:Any

    }
}