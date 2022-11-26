package com.serranocjm.marvelchartestapp.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.serranocjm.marvelchartestapp.repository.CharacterRepository
import com.serranocjm.marvelchartestapp.ui.adapter.item.model.HeroItemModel
import com.serranocjm.marvelchartestapp.utils.general.Constants
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.HttpException

class HeroListPagingSource : PagingSource<Int, HeroItemModel>(), KoinComponent {

    private val characterRepository: CharacterRepository by inject()

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HeroItemModel> {
        return try {
            val currentPage: Int = params.key ?: 0
            val data =
                characterRepository.getCharacterList(currentPage) // In this case, the currentPage is the offset, which should be X amount of results per page
            val responseData = mutableListOf<HeroItemModel>()

            data?.forEach { item ->
                responseData.add(HeroItemModel((item)))
            }

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 0) null else -1,
                nextKey = currentPage.plus(Constants.API_OFFSET_SIZE)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, HeroItemModel>): Int? {
        return null
    }
}
