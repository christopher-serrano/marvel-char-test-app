package com.serranocjm.marvelchartestapp.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.serranocjm.marvelchartestapp.BuildConfig
import com.serranocjm.marvelchartestapp.network.api.Endpoints
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun networkTestModule(baseUrl: String) = module {
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.HEADERS
                }
            }
        )
        .build()

    Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(baseUrl)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Endpoints::class.java)
}

/**
 * Creates Mockwebserver instance for testing
 */
val mockWebServerTestModule = module {

    factory {
        MockWebServer()
    }
}
