package org.wahid.borutoappversion1.domain.use_cases.get_selected_hero

import org.wahid.borutoappversion1.data.repository.Repository
import org.wahid.borutoappversion1.domain.model.Hero

class GetSelectedHero(
    private val repository: Repository,
) {
    suspend operator fun invoke(heroId: Int): Hero {
        return repository.getSelectedHeo(heroId = heroId)
    }
}