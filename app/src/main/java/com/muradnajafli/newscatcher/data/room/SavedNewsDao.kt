package com.muradnajafli.newscatcher.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.muradnajafli.newscatcher.domain.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedNewsDao {

    @Upsert
    suspend fun upsert(article: Article)

    @Delete
    suspend fun delete(article: Article)

    @Query(value = "SELECT * FROM saved_articles")
    fun readAllData(): Flow<List<Article>>

    @Query("SELECT * FROM saved_articles WHERE link = :link")
    suspend fun getNewsByUrl(link: String?): Article?

}