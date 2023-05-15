package com.example.herohub.data.source.remote

import android.util.Log
import com.example.herohub.MyApplication
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.util.concurrent.TimeUnit

class OfflineInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        Log.d(TAG, "offline interceptor: called.")
        var request = chain.request()

        // prevent caching when network is on. For that we use the "networkInterceptor"
        if (!MyApplication.hasNetwork()) {
            val cacheControl = CacheControl.Builder()
                .maxStale(7, TimeUnit.DAYS)
                .build()

            request = request.newBuilder()
                .removeHeader(HEADER_PRAGMA)
                .removeHeader(HEADER_CACHE_CONTROL)
                .cacheControl(cacheControl)
                .build()
        }

        return chain.proceed(request)
    }

    companion object {
        private const val TAG = "OfflineInterceptor"
        private const val HEADER_PRAGMA = "Pragma"
        private const val HEADER_CACHE_CONTROL = "Cache-Control"
    }
}
