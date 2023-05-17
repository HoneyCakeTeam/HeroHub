package com.example.herohub.data.remote

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.herohub.HeroHub
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object MarvelApi {
    private const val BASE_URL = "https://gateway.marvel.com/v1/public/"
    private val logInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }
    private val client = OkHttpClient.Builder().apply {
        addInterceptor(logInterceptor)
        addInterceptor(MarvelInterceptor())
        addInterceptor(ChuckerInterceptor(HeroHub.instance!!.applicationContext))
    }.build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    val marvelService: MarvelService = retrofit.create(MarvelService::class.java)
}