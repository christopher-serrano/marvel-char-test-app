package com.serranocjm.marvelchartestapp.network.interceptor

import com.serranocjm.marvelchartestapp.BuildConfig
import com.serranocjm.marvelchartestapp.utils.general.toHex
import com.serranocjm.marvelchartestapp.utils.general.toMd5
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class RequestInterceptorImpl : RequestInterceptor {

    override fun getMd5Hash(): String {
        val hashString =
            "${timestamp}${BuildConfig.PRIVATE_KEY}${BuildConfig.PUBLIC_KEY}"
        val digest = hashString.toMd5()
        return digest.toHex()
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val originalHttpUrl: HttpUrl = original.url

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("ts", timestamp)
            .addQueryParameter("apikey", BuildConfig.PUBLIC_KEY)
            .addQueryParameter("hash", getMd5Hash())
            .build()

        // Request customization: add request headers
        val requestBuilder: Request.Builder = original.newBuilder()
            .url(url)

        val request: Request = requestBuilder.build()
        return chain.proceed(request)
    }

    companion object {
        var timestamp = System.currentTimeMillis().toString()
    }
}
