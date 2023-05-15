package com.example.herohub.data.source.remote

import android.util.Log
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

class CacheInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        Log.d(TAG, "Online Interceptor: called.")
        val response: Response = chain.proceed(chain.request())
        val cacheControl = CacheControl.Builder()
            .maxAge(MAX_AGE, TimeUnit.DAYS)
            .build()

        return response.newBuilder()
            .header(HEADER_CACHE_CONTROL, cacheControl.toString())
            .build()
    }

    companion object {
        private const val MAX_AGE = 10
        private const val TAG = "OnlineInterceptor"
        private const val HEADER_CACHE_CONTROL = "Cache-Control"
    }
}