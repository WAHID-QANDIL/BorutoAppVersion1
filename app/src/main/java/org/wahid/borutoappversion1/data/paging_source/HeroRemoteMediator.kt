package org.wahid.borutoappversion1.data.paging_source

import android.util.Log
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

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Hero>,
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    getRemoteKeyClosestToCurrentPosition(state)?.nextPage?.minus(1) ?: 1
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                    remoteKeys.prevPage
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                }

                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                    remoteKeys.nextPage
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                }
            }

            Log.d("LOAD_TYPE", "load: ${loadType.name}")
            // Log the API response before storing in DB
            val response = borutoApi.getAllHeroes(page = page)
            Log.d("API_RESPONSE", "Heroes from API: ${response.heroes}")

            borutoDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    heroRemoteKeysDao.deleteAllRemoteKeys()
                    heroDao.deleteAllHeroes()
                }

                val previousPage = if (page == 1) null else page - 1
                val nextPage = if (page == 5) null else page + 1


                val keys: List<HeroRemoteKeys> = response.heroes.map { hero ->
                    HeroRemoteKeys(
                        id = hero.id,
                        prevPage = previousPage,
                        nextPage = nextPage,
                        lastUpdated = response.lastUpdated
                    )
                }
                heroRemoteKeysDao.addAllRemoteKeys(keys)
                if (response.heroes.isNotEmpty()) {
                    heroDao.addHeroes(heroes = response.heroes)
                }

                // Log data stored in the database.
                val heroesInDb = heroDao.getAllHeroesSync()
                Log.d("DB_DATA", "Heroes stored in DB: $heroesInDb")
            }

            MediatorResult.Success(endOfPaginationReached = response.nextPage == null)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    override suspend fun initialize(): InitializeAction {
        val currentTime = System.currentTimeMillis()
        val lastUpdated = heroRemoteKeysDao.getRemoteKey(1)?.lastUpdated ?: 0L
        val cacheTime = 720

        val diffTime = (currentTime - lastUpdated) / 1000 / 60

        return if (diffTime <= cacheTime) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }

    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Hero>): HeroRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                heroRemoteKeysDao.getRemoteKey(id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Hero>): HeroRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }
            ?.data?.firstOrNull()?.let { hero ->
                heroRemoteKeysDao.getRemoteKey(hero.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Hero>): HeroRemoteKeys? {
        val lastItem = state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
        Log.d("RemoteMediator", "Last item id: ${lastItem?.id}")
        return lastItem?.let { hero ->
            val key = heroRemoteKeysDao.getRemoteKey(hero.id)
            Log.d("RemoteMediator", "Remote key for last item: $key")
            key
        }
    }
}