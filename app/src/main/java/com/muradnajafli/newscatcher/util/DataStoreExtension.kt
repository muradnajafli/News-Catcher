package com.muradnajafli.newscatcher.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

const val SETTINGS_KEY = "settings"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = SETTINGS_KEY)
