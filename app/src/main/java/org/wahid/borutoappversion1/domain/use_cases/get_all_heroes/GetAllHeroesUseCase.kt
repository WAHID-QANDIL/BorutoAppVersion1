package org.wahid.borutoappversion1.domain.use_cases.get_all_heroes

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.wahid.borutoappversion1.data.repository.Repository
import org.wahid.borutoappversion1.domain.model.Hero

class GetAllHeroesUseCase(
    private val repository: Repository
) {

    suspend operator fun invoke(): Flow<PagingData<Hero>>{
        return repository.getAllHeroes()
    }
}