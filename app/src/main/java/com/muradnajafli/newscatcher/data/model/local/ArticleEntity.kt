package com.muradnajafli.newscatcher.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_articles")
data class ArticleEntity(
    val author: String?,
    val excerpt: String?,
    @PrimaryKey val link: String,
    val media: String?,
    val publishedDate: String?,
    val summary: String?,
    val title: String?,
    val topic: String?
)
