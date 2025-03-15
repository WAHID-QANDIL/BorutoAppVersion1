package org.wahid.borutoappversion1.data.repository

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import okio.IOException
import org.wahid.borutoappversion1.data.local.DataStoreInit
import org.wahid.borutoappversion1.domain.repository.DatastoreOperations


class DatastoreOperationsImpl(
    dataStorePreferences: DataStoreInit,
) : DatastoreOperations {
    private val dataStore = dataStorePreferences.getInstance
    private val preferencesKey = dataStorePreferences.preferencesKey

    override suspend fun saveOnBoardingState(isCompleted: Boolean) {

        dataStore.edit { preferences ->
            preferences[preferencesKey] = isCompleted
        }
    }

    override fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data.catch { exp ->
            if (exp is IOException) emit(emptyPreferences())
            else throw exp
        }.map { pref ->
            val onBoardingState = pref[preferencesKey] ?: false
            onBoardingState
        }

    }
}