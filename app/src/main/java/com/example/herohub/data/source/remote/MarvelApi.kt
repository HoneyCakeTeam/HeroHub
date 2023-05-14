package com.example.herohub.data.source.remote

import android.app.Application
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

object MarvelApi {
    private const val BASE_URL = "https://gateway.marvel.com/v1/public/"
    private const val HTTP_CACHE = "http-cache"
    private const val MAX_SIZE = 10L * 1024L * 1024L// 10 MiB

    private val logInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }
    private val client = OkHttpClient().newBuilder().apply {
        cache(Cache(File(Application().applicationContext.cacheDir, HTTP_CACHE), MAX_SIZE))
        addInterceptor(logInterceptor)
        addInterceptor(MarvelInterceptor())
        addNetworkInterceptor(CacheInterceptor())
    }.build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    val marvelService: MarvelService = retrofit.create(MarvelService::class.java)
}