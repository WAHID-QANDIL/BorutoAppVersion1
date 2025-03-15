package org.wahid.borutoappversion1.domain.use_cases.onboarding

import kotlinx.coroutines.flow.Flow
import org.wahid.borutoappversion1.data.repository.Repository

class ReadOnBoardingState (
    private val repository: Repository
){

    operator fun invoke():Flow<Boolean> = repository.readOnBoardingState()

}