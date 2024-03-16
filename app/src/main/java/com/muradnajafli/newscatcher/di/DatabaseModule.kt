package com.muradnajafli.newscatcher.di

import android.app.Application
import androidx.room.Room
import com.muradnajafli.newscatcher.data.room.SavedNewsDao
import com.muradnajafli.newscatcher.data.room.SavedNewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): SavedNewsDatabase {
        return Room.databaseBuilder(
            app,
            SavedNewsDatabase::class.java,
            "saved_news_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideFavMovieDao(db: SavedNewsDatabase): SavedNewsDao {
        return db.savedNewsDao()
    }

}