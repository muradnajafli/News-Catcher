package com.muradnajafli.newscatcher.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.muradnajafli.newscatcher.data.repository.local.DataStoreRepositoryImpl
import com.muradnajafli.newscatcher.utils.dataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataStoreModule {

    @Provides
    @Singleton
    fun provideDataStorePreferences(
        @ApplicationContext context: Context
    ) = context.dataStore


    @Provides
    @Singleton
    fun provideDataStoreManager(
        dataStore: DataStore<Preferences>
    ): DataStoreRepositoryImpl {
        return DataStoreRepositoryImpl(dataStore)
    }

}