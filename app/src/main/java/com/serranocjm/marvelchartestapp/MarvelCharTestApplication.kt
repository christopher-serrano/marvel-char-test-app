package com.serranocjm.marvelchartestapp

import android.app.Application
import com.serranocjm.marvelchartestapp.di.CoreModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MarvelCharTestApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
        CoreModule.init()
    }

    /**
     * Initialize dependency injection
     */
    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@MarvelCharTestApplication)
        }
    }
}
