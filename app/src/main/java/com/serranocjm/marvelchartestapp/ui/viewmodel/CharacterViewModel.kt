package com.serranocjm.marvelchartestapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.serranocjm.marvelchartestapp.data.model.character.Hero
import com.serranocjm.marvelchartestapp.repository.CharacterRepository
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CharacterViewModel : BaseViewModel(), KoinComponent {

    // Inject repository directly
    private val characterRepository: CharacterRepository by inject()

    // LiveData variables
    val heroList = MutableLiveData<List<Hero>?>()
    val heroDetail = MutableLiveData<Hero?>()

    // Offset control variable
    var offsetValue = MutableLiveData<Int?>()

    // Public functions (to be accessed from the view)

    // Set offset valur
    fun setRequestOffset(offset: Int) {
        offsetValue.postValue(offset)
    }

    // Get Hero List
    fun getHeroList(offset: Int) {
        viewModelScope.launch {
            getHeroListAsync(offset)
        }
    }

    // Get Hero etail
    fun getHeroDetail(id: Int) {
        viewModelScope.launch {
            getHeroDetailAsync(id)
        }
    }

    // Private suspend functions

    // Async get hero list
    private suspend fun getHeroListAsync(offset: Int) {
        val result = kotlin.runCatching {
            showLoading()
            characterRepository.getCharacterList(offset)
        }
        with(result) {
            dismissLoading()
            onSuccess {
                heroList.postValue(it)
            }
            onFailure {
                onError.postValue(it.message)
            }
        }
    }

    // Async get hero detail
    private suspend fun getHeroDetailAsync(id: Int) {
        val result = kotlin.runCatching {
            showLoading()
            characterRepository.getCharacterDetail(id)
        }
        with(result) {
            dismissLoading()
            onSuccess {
                heroDetail.postValue(it)
            }
            onFailure {
                onError.postValue(it.message)
            }
        }
    }
}
