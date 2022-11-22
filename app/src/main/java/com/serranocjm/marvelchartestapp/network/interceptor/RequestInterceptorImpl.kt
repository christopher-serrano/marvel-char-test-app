package com.serranocjm.marvelchartestapp.network.interceptor

import com.serranocjm.marvelchartestapp.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class RequestInterceptorImpl : RequestInterceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val originalHttpUrl: HttpUrl = original.url

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("apikey", BuildConfig.PUBLIC_KEY)
            .build()

        // Request customization: add request headers
        val requestBuilder: Request.Builder = original.newBuilder()
            .url(url)

        val request: Request = requestBuilder.build()
        return chain.proceed(request)
    }
}
