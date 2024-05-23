package com.muradnajafli.newscatcher.data.repository.local

import com.muradnajafli.newscatcher.data.mapper.EntityMapper.toArticle
import com.muradnajafli.newscatcher.data.mapper.EntityMapper.toEntity
import com.muradnajafli.newscatcher.data.room.SavedNewsDao
import com.muradnajafli.newscatcher.domain.model.Article
import com.muradnajafli.newscatcher.domain.repository.local.SavedNewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SavedNewsRepositoryImpl @Inject constructor(
    private val savedNewsDao: SavedNewsDao
) : SavedNewsRepository {
    override suspend fun saveNews(article: Article) {
        savedNewsDao.upsert(article.toEntity())
    }

    override suspend fun deleteSavedNews(article: Article) {
        savedNewsDao.delete(article.toEntity())
    }

    override suspend fun getSavedNews(): Flow<List<Article?>> {
        return savedNewsDao.readAllData().map { list ->
            list.map { it.toArticle() }
        }
    }

    override suspend fun getNewsByUrl(link: String): Article? {
        return savedNewsDao.getNewsByUrl(link).toArticle()
    }

}