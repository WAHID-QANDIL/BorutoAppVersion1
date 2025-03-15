package org.wahid.borutoappversion1.domain.use_cases.onboarding

import org.wahid.borutoappversion1.data.repository.Repository

class SaveOnBoardingUseCase (
    private val repository: Repository
) {

    suspend operator fun invoke(isCompleted:Boolean) = repository.saveOnBoardingState(isCompleted = isCompleted)


}