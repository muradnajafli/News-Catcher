package com.muradnajafli.newscatcher.domain.repository.local

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun updateLanguage(language: String)
    fun readLanguage(): Flow<String>
}