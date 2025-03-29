package org.wahid.borutoappversion1.domain.use_cases.search_heroes

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.wahid.borutoappversion1.data.repository.Repository
import org.wahid.borutoappversion1.domain.model.Hero

class SearchHeroes(
    private val repository: Repository,
) {
    operator fun invoke(query: String): Flow<PagingData<Hero>> {
        return repository.searchForHero(query = query)
    }

}