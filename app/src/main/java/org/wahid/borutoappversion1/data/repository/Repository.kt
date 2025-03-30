package org.wahid.borutoappversion1.data.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.wahid.borutoappversion1.domain.model.Hero
import org.wahid.borutoappversion1.domain.repository.DatastoreOperations
import org.wahid.borutoappversion1.domain.repository.LocalDataSource
import org.wahid.borutoappversion1.domain.repository.RemoteDataSource
import javax.inject.Inject

class Repository @Inject constructor(
    private val local: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val datastoreOperations: DatastoreOperations,
) {
    suspend fun saveOnBoardingState(isCompleted: Boolean) {
        return datastoreOperations.saveOnBoardingState(isCompleted = isCompleted)
    }

    fun readOnBoardingState(): Flow<Boolean> {
        return datastoreOperations.readOnBoardingState()
    }

    suspend fun getSelectedHeo(heroId: Int): Hero {
        return local.getSelectedHero(heroId = heroId)
    }

    fun getAllHeroes(): Flow<PagingData<Hero>> = remoteDataSource.getAllHeroes()

    fun searchForHero(query: String): Flow<PagingData<Hero>> {
        return remoteDataSource.searchForHero(query = query)

    }


}