package org.wahid.borutoappversion1.data.repository

import kotlinx.coroutines.flow.Flow
import org.wahid.borutoappversion1.domain.repository.DatastoreOperations
import javax.inject.Inject

class Repository @Inject constructor(
    private val datastoreOperations: DatastoreOperations
) {
    suspend fun saveOnBoardingState(isCompleted: Boolean) {
        return datastoreOperations.saveOnBoardingState(isCompleted = isCompleted)
    }

    fun readOnBoardingState(): Flow<Boolean> {
        return datastoreOperations.readOnBoardingState()
    }


}