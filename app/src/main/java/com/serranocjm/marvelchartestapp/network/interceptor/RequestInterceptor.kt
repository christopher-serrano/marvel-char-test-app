package com.serranocjm.marvelchartestapp.network.interceptor

import okhttp3.Interceptor

interface RequestInterceptor : Interceptor {
    fun getMd5Hash(): String
}
