package com.muradnajafli.newscatcher.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.muradnajafli.newscatcher.data.model.local.ArticleEntity
import com.muradnajafli.newscatcher.data.model.remote.ArticleDto
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedNewsDao {

    @Upsert
    suspend fun upsert(articleEntity: ArticleEntity)

    @Delete
    suspend fun delete(articleEntity: ArticleEntity)

    @Query(value = "SELECT * FROM saved_articles")
    fun readAllData(): Flow<List<ArticleEntity>>

    @Query("SELECT * FROM saved_articles WHERE link = :link")
    suspend fun getNewsByUrl(link: String): ArticleEntity

}