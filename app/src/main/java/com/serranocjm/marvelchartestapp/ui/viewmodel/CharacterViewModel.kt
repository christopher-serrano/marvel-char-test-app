package com.serranocjm.marvelchartestapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.serranocjm.marvelchartestapp.data.model.character.Hero
import com.serranocjm.marvelchartestapp.repository.CharacterRepository
import com.serranocjm.marvelchartestapp.utils.general.Constants
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CharacterViewModel : BaseViewModel(), KoinComponent {

    // Inject repository directly
    private val characterRepository: CharacterRepository by inject()

    // LiveData variables
    val heroList = MutableLiveData<List<Hero>?>()
    val heroDetail = MutableLiveData<Hero?>()

    // Hero list Pager (flowable - test)
    val heroListFlow =
        characterRepository.getCharacterListFlow(Constants.API_OFFSET_SIZE).cachedIn(viewModelScope)

    // Get Hero detail
    fun getHeroDetail(id: Int) {
        viewModelScope.launch {
            getHeroDetailAsync(id)
        }
    }

    // Private suspend functions

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
