package com.muradnajafli.newscatcher.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : DataStoreManagement {

    companion object PreferencesKeys {
        private const val LANGUAGE_KEY = "language"
        val languageKey = stringPreferencesKey(LANGUAGE_KEY)
    }

    override suspend fun updateLanguage(language: String) {
        dataStore.edit { settings ->
            settings[languageKey] = language
        }
    }

    override fun readLanguage(): Flow<String> {
        return dataStore.data.map { settings ->
            settings[languageKey] ?: "en"
        }
    }

}