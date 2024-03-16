package com.muradnajafli.newscatcher.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.muradnajafli.newscatcher.domain.model.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class SavedNewsDatabase : RoomDatabase() {
    abstract fun savedNewsDao(): SavedNewsDao
}