package com.example.rssnews.model

import com.example.rssnews.model.pojo.Rss
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.http.GET
import javax.xml.datatype.DatatypeConstants.SECONDS
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


interface VestiRetrofitInterface{

    @GET("vesti.rss")
    suspend fun getNews(): Rss
}

object VestiAPIService {

    val vestiAPIService:VestiRetrofitInterface by lazy {

        create()
    }

    private fun create(): VestiRetrofitInterface {

        val client = OkHttpClient.Builder()
        client.connectTimeout(15, TimeUnit.SECONDS)
        client.readTimeout(15, TimeUnit.SECONDS)
        client.writeTimeout(15, TimeUnit.SECONDS)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.vesti.ru/")
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .client(client.build())
            .build()
        return retrofit.create(VestiRetrofitInterface::class.java)
    }
}