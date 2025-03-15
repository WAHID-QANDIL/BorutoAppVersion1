package org.wahid.borutoappversion1.domain.repository

import kotlinx.coroutines.flow.Flow

interface DatastoreOperations {

    suspend fun saveOnBoardingState(isCompleted:Boolean)

    fun readOnBoardingState():Flow<Boolean>

}