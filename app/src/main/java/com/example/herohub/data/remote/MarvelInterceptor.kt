package com.example.herohub.data.remote

import com.example.herohub.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.security.MessageDigest
import javax.inject.Inject

class MarvelInterceptor @Inject constructor() : Interceptor {
    private val timeStamp = System.currentTimeMillis().toString()
    private val hash = getMarvelHash(timeStamp)

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.newBuilder()
            .addQueryParameter(TIME_STAMP, timeStamp)
            .addQueryParameter(API_KEY, BuildConfig.PUBLIC_KEY)
            .addQueryParameter(HASH, hash)
            .build()
        val newRequest = request.newBuilder().url(url).build()

        return chain.proceed(newRequest)
    }

    private fun getMarvelHash(ts: String): String {
        val input = "$ts${BuildConfig.PRIVATE_KEY}${BuildConfig.PUBLIC_KEY}"
        val bytes = MessageDigest.getInstance(MD5_ALGORITHM).digest(input.toByteArray())

        return bytes.joinToString("") { "%02x".format(it) }
    }

    companion object {
        private const val MD5_ALGORITHM = "MD5"
        private const val TIME_STAMP = "ts"
        private const val API_KEY = "apikey"
        private const val HASH = "hash"
    }
}