package com.serranocjm.marvelchartestapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler

open class BaseViewModel : ViewModel() {
    val loadingState = MutableLiveData<Boolean>() // UI loader state
    val onSuccess = MutableLiveData<Any>()
    val onError = MutableLiveData<String>()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    protected fun showLoading() = loadingState.postValue(true)
    protected fun dismissLoading() = loadingState.postValue(false)
}
