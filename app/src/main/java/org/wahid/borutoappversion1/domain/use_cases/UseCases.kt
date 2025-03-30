package org.wahid.borutoappversion1.domain.use_cases

import org.wahid.borutoappversion1.domain.use_cases.get_all_heroes.GetAllHeroesUseCase
import org.wahid.borutoappversion1.domain.use_cases.get_selected_hero.GetSelectedHero
import org.wahid.borutoappversion1.domain.use_cases.onboarding.ReadOnBoardingState
import org.wahid.borutoappversion1.domain.use_cases.onboarding.SaveOnBoardingUseCase
import org.wahid.borutoappversion1.domain.use_cases.search_heroes.SearchHeroes

data class UseCases(
    val readOnBoardingState: ReadOnBoardingState,
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val getAllHeroesUseCase: GetAllHeroesUseCase,
    val searchHeroes: SearchHeroes,
    val getSelectedHero: GetSelectedHero,
)