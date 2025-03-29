package org.wahid.borutoappversion1.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.wahid.borutoappversion1.domain.model.Hero

interface RemoteDataSource {

    fun getAllHeroes(): Flow<PagingData<Hero>>
    fun searchForHero(query: String): Flow<PagingData<Hero>>
}