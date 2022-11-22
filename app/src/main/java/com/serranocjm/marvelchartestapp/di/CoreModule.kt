package com.serranocjm.marvelchartestapp.di

import com.serranocjm.marvelchartestapp.di.modules.networkModule
import com.serranocjm.marvelchartestapp.di.modules.repositoryModule
import com.serranocjm.marvelchartestapp.di.modules.utilsModule
import com.serranocjm.marvelchartestapp.di.modules.viewModelModule
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module

object CoreModule {
    private val modules: List<Module>
        get() = listOf(
            networkModule,
            repositoryModule,
            viewModelModule,
            utilsModule
        )

    fun init() = loadKoinModules(modules)
}
