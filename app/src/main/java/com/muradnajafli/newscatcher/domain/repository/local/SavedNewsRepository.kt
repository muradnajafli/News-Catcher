package com.muradnajafli.newscatcher.domain.repository.local

import com.muradnajafli.newscatcher.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface SavedNewsRepository {
    suspend fun getSavedNews(): Flow<List<Article?>>
    suspend fun saveNews(article: Article)
    suspend fun deleteSavedNews(article: Article)
    suspend fun getNewsByUrl(link: String): Article?
}