package com.muradnajafli.newscatcher.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.muradnajafli.newscatcher.data.model.local.ArticleEntity
import com.muradnajafli.newscatcher.data.model.remote.ArticleDto

@Database(entities = [ArticleEntity::class], version = 1, exportSchema = false)
abstract class SavedNewsDatabase : RoomDatabase() {
    abstract fun savedNewsDao(): SavedNewsDao
}