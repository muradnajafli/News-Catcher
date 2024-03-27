package com.muradnajafli.newscatcher.di

import com.muradnajafli.newscatcher.data.repository.local.DataStoreRepositoryImpl
import com.muradnajafli.newscatcher.data.repository.local.SavedNewsRepositoryImpl
import com.muradnajafli.newscatcher.data.repository.remote.LatestHeadlinesRepositoryImpl
import com.muradnajafli.newscatcher.data.repository.remote.SearchNewsRepositoryImpl
import com.muradnajafli.newscatcher.domain.repository.local.DataStoreRepository
import com.muradnajafli.newscatcher.domain.repository.local.SavedNewsRepository
import com.muradnajafli.newscatcher.domain.repository.remote.LatestHeadlinesRepository
import com.muradnajafli.newscatcher.domain.repository.remote.SearchNewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindSavedNewsRepository(
        savedNewsRepositoryImpl: SavedNewsRepositoryImpl
    ): SavedNewsRepository

    @Binds
    @Singleton
    abstract fun bindLatestHeadlinesRepository(
        latestHeadlinesRepositoryImpl: LatestHeadlinesRepositoryImpl
    ): LatestHeadlinesRepository

    @Binds
    @Singleton
    abstract fun bindSearchNewsRepository(
       searchNewsRepositoryImpl: SearchNewsRepositoryImpl
    ): SearchNewsRepository

    @Binds
    @Singleton
    abstract fun bindDataStoreRepository(
        dataStoreRepositoryImpl: DataStoreRepositoryImpl
    ): DataStoreRepository

}