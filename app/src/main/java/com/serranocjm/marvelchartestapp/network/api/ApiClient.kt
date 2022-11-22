package com.serranocjm.marvelchartestapp.network.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.serranocjm.marvelchartestapp.BuildConfig
import com.serranocjm.marvelchartestapp.network.interceptor.RequestInterceptorImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.component.KoinComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient : KoinComponent {
    companion object {
        operator fun invoke(): Endpoints {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = if (BuildConfig.DEBUG) {
                            HttpLoggingInterceptor.Level.BODY
                        } else {
                            HttpLoggingInterceptor.Level.NONE
                        }
                    }
                )
                .addInterceptor(RequestInterceptorImpl())
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Endpoints::class.java)
        }
    }
}
