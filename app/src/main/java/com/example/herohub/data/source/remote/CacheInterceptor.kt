package com.example.herohub.data.source.remote

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

class CacheInterceptor:Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response: Response = chain.proceed(chain.request())
        val cacheControl = CacheControl.Builder()
            .maxAge(MAX_AGE, TimeUnit.DAYS)
            .build()

        return response.newBuilder()
            .header(CACHE_CONTROL, cacheControl.toString())
            .build()
    }
    companion object{
        private const val MAX_AGE  = 10
        private const val CACHE_CONTROL = "Cache-Control"
    }
}