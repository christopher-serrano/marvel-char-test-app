package com.serranocjm.marvelchartestapp.di.modules

import com.serranocjm.marvelchartestapp.network.api.ApiClient
import com.serranocjm.marvelchartestapp.network.interceptor.RequestInterceptor
import com.serranocjm.marvelchartestapp.network.interceptor.RequestInterceptorImpl
import com.serranocjm.marvelchartestapp.repository.CharacterRepository
import com.serranocjm.marvelchartestapp.repository.CharacterRepositoryImpl
import com.serranocjm.marvelchartestapp.ui.viewmodel.CharacterViewModel
import com.serranocjm.marvelchartestapp.utils.custom.ImageLoader
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    // Initialize network-related classes
    single { ApiClient.invoke() }
    single<RequestInterceptor> { RequestInterceptorImpl() }
}

val repositoryModule = module {
    // Initialize repository-related classes
    factory<CharacterRepository> { CharacterRepositoryImpl() }
}

val viewModelModule = module {
    // Initialize viewmodel-related classes
    viewModel { CharacterViewModel() }
}

val utilsModule = module {
    // Initialize utils-related classes
    factory { ImageLoader(get()) }
}
