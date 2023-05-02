package com.example.herohub

import okhttp3.Interceptor
import okhttp3.Response
import java.security.MessageDigest

class MarvelInterceptor : Interceptor {
    private val ts = System.currentTimeMillis().toString()
    private val hash = getMarvelHash(ts)

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.newBuilder()
            .addQueryParameter(TIME_STAMP, ts)
            .addQueryParameter(API_KEY, BuildConfig.PUBLIC_KEY)
            .addQueryParameter(HASH, hash)
            .build()
        val newRequest = request.newBuilder().url(url).build()

        return chain.proceed(newRequest)
    }

    private fun getMarvelHash(ts: String): String {
        val input = "$ts${BuildConfig.PRIVATE_KEY}${BuildConfig.PUBLIC_KEY}"
        val bytes = MessageDigest.getInstance("MD5").digest(input.toByteArray())

        return bytes.joinToString("") { "%02x".format(it) }
    }

    companion object {
        private const val TIME_STAMP = "ts"
        private const val API_KEY = "apikey"
        private const val HASH = "hash"
    }
}