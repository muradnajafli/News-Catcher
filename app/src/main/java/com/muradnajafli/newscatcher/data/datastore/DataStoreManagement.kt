package com.muradnajafli.newscatcher.data.datastore

import kotlinx.coroutines.flow.Flow

interface DataStoreManagement {
    suspend fun updateLanguage(language: String)
    fun readLanguage(): Flow<String>
}