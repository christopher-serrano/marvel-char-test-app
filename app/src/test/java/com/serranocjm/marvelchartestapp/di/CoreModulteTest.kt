package com.serranocjm.marvelchartestapp.di

import com.serranocjm.marvelchartestapp.di.modules.repositoryModule
import com.serranocjm.marvelchartestapp.di.modules.viewModelModule
import org.koin.core.module.Module

fun configureAppTestModules(baseUrl: String): List<Module> {
    return listOf(
        mockWebServerTestModule,
        networkTestModule(baseUrl),
        repositoryModule,
        viewModelModule
    )
}