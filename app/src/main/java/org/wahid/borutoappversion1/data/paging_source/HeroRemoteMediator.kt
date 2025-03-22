package org.wahid.borutoappversion1.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import org.wahid.borutoappversion1.data.local.BorutoDatabase
import org.wahid.borutoappversion1.data.remote.retrofit.BorutoApi
import org.wahid.borutoappversion1.domain.model.Hero
import org.wahid.borutoappversion1.domain.model.HeroRemoteKeys
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class HeroRemoteMediator @Inject constructor(
    private val borutoApi: BorutoApi,
    private val borutoDatabase: BorutoDatabase,
) : RemoteMediator<Int, Hero>() {

    private val heroDao = borutoDatabase.heroDao()
    private val heroRemoteKeysDao = borutoDatabase.heroRemoteKeysDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Hero>): MediatorResult {

        return try {
            val response = borutoApi.getAllHeroes(page = 1)

            if (response.heroes.isNotEmpty()) {
                borutoDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        heroDao.deleteAllHeroes()
                        heroRemoteKeysDao.deleteAllRemoteKeys()
                    }

                    val previousPage = response.previousPage
                    val nextPage = response.nextPage
                    val keys: List<HeroRemoteKeys> = response.heroes.map { hero ->
                        HeroRemoteKeys(
                            id = hero.id,
                            prevPage = previousPage,
                            nextPage = nextPage
                        )
                    }

                    heroRemoteKeysDao.addAllRemoteKeys(keys)
                }
            }
            MediatorResult.Success(endOfPaginationReached = response.nextPage == null)

        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }
}