package org.wahid.borutoappversion1.domain.use_cases

import org.wahid.borutoappversion1.domain.use_cases.get_all_heroes.GetAllHeroesUseCase
import org.wahid.borutoappversion1.domain.use_cases.onboarding.ReadOnBoardingState
import org.wahid.borutoappversion1.domain.use_cases.onboarding.SaveOnBoardingUseCase

data class UseCases(
    val readOnBoardingState: ReadOnBoardingState,
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val getAllHeroesUseCase: GetAllHeroesUseCase
)