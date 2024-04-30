package com.muradnajafli.newscatcher.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

const val DROPDOWN_KEY = "dropdown_key"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DROPDOWN_KEY)