package com.serranocjm.marvelchartestapp.di

import com.serranocjm.marvelchartestapp.di.modules.* // ktlint-disable no-wildcard-imports
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module

object CoreModule {
    private val modules: List<Module>
        get() = listOf(
            networkModule,
            dataModule,
            repositoryModule,
            viewModelModule,
            utilsModule
        )

    fun init() = loadKoinModules(modules)
}
