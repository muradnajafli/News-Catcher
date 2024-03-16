package com.muradnajafli.newscatcher.data.repository.local

import com.muradnajafli.newscatcher.data.room.SavedNewsDao
import com.muradnajafli.newscatcher.domain.model.Article
import com.muradnajafli.newscatcher.domain.repository.local.SavedNewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SavedNewsRepositoryImpl @Inject constructor(
    private val savedNewsDao: SavedNewsDao
) : SavedNewsRepository {

    override suspend fun getSavedNews(): Flow<List<Article>> {
        return savedNewsDao.readAllData()
    }

    override suspend fun saveNews(article: Article) {
        savedNewsDao.upsert(article)
    }

    override suspend fun deleteSavedNews(article: Article) {
        savedNewsDao.delete(article)
    }

    override suspend fun getNewsByUrl(link: String?): Article? {
        return savedNewsDao.getNewsByUrl(link)
    }

}