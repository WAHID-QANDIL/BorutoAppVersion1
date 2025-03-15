package org.wahid.borutoappversion1.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import org.wahid.borutoappversion1.utils.Constants.DATASTORE_NAME
import org.wahid.borutoappversion1.utils.Constants.DATASTORE_PREFERENCES_KEY_NAME

class DataStoreInit(context: Context) {
    private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = DATASTORE_NAME)
    val preferencesKey = booleanPreferencesKey(name = DATASTORE_PREFERENCES_KEY_NAME)
    val getInstance = context.dataStore

}