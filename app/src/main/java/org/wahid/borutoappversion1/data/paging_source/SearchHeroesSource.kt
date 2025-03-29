package org.wahid.borutoappversion1.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import org.wahid.borutoappversion1.data.remote.retrofit.BorutoApi
import org.wahid.borutoappversion1.domain.model.Hero
import javax.inject.Inject

class SearchHeroesSource @Inject constructor(
    private val brorutoApi: BorutoApi,
    private val query: String,
) : PagingSource<Int, Hero>() {
    override fun getRefreshKey(state: PagingState<Int, Hero>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hero> {
        return try {
            val apiResponse = brorutoApi.searchHeroes(name = query)
            val heroes = apiResponse.heroes
            if (heroes.isNotEmpty()) {
                LoadResult.Page(
                    data = heroes,
                    nextKey = apiResponse.nextPage,
                    prevKey = apiResponse.previousPage
                )
            } else {

                LoadResult.Page(
                    data = emptyList(),
                    nextKey = null,
                    prevKey = null
                )
            }


        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}