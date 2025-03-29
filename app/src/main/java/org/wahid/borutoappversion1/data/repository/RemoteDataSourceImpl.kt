package org.wahid.borutoappversion1.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.wahid.borutoappversion1.data.local.BorutoDatabase
import org.wahid.borutoappversion1.data.paging_source.HeroRemoteMediator
import org.wahid.borutoappversion1.data.paging_source.SearchHeroesSource
import org.wahid.borutoappversion1.data.remote.retrofit.BorutoApi
import org.wahid.borutoappversion1.domain.model.Hero
import org.wahid.borutoappversion1.domain.repository.RemoteDataSource
import org.wahid.borutoappversion1.utils.Constants.NUMBER_OF_HEROES_PER_PAGE

@OptIn(ExperimentalPagingApi::class)
class RemoteDataSourceImpl(
    private val borutoApi: BorutoApi,
    private val borutoDatabase: BorutoDatabase
): RemoteDataSource {
    private val heroDao = borutoDatabase.heroDao()

    override fun getAllHeroes(): Flow<PagingData<Hero>> {
        val pagingSourceFactory = { heroDao.getAllHeroes() }
        return Pager(
            config = PagingConfig(pageSize = NUMBER_OF_HEROES_PER_PAGE),
            remoteMediator = HeroRemoteMediator(
                borutoApi = borutoApi,
                borutoDatabase = borutoDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchForHero(query: String): Flow<PagingData<Hero>> {
        return Pager(
            config = PagingConfig(
                pageSize = NUMBER_OF_HEROES_PER_PAGE,
            ),
            pagingSourceFactory = {
                SearchHeroesSource(
                    brorutoApi = borutoApi,
                    query = query
                )
            }
        ).flow
    }


}