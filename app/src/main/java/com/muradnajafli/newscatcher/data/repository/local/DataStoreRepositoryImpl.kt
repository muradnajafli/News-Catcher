package com.muradnajafli.newscatcher.data.repository.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.muradnajafli.newscatcher.domain.repository.local.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : DataStoreRepository {

    companion object PreferencesKeys {
        val languageKey = stringPreferencesKey("language")
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