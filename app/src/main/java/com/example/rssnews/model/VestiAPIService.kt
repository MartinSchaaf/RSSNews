package com.example.rssnews.model

import com.example.rssnews.model.POJO.Response
import com.example.rssnews.model.POJO.Rss
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.http.GET



interface VestiRetrofitInterface{

    @GET("vesti.rss")
    suspend fun getNews(): Rss
}

object VestiAPIService {

    val vestiAPIService:VestiRetrofitInterface by lazy {

        create()
    }

    private fun create(): VestiRetrofitInterface {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.vesti.ru/")
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()
        return retrofit.create(VestiRetrofitInterface::class.java)
    }
}