package com.muradnajafli.newscatcher.di

import com.muradnajafli.newscatcher.domain.repository.local.DataStoreRepository
import com.muradnajafli.newscatcher.domain.repository.local.SavedNewsRepository
import com.muradnajafli.newscatcher.domain.repository.remote.LatestHeadlinesRepository
import com.muradnajafli.newscatcher.domain.repository.remote.SearchNewsRepository
import com.muradnajafli.newscatcher.domain.usecase.home.GetLatestHeadlinesUseCase
import com.muradnajafli.newscatcher.domain.usecase.home.GetLatestHeadlinesUseCaseImpl
import com.muradnajafli.newscatcher.domain.usecase.home.GetNewsFromSearchUseCase
import com.muradnajafli.newscatcher.domain.usecase.home.GetNewsFromSearchUseCaseImpl
import com.muradnajafli.newscatcher.domain.usecase.detail.AddSavedNewsUseCase
import com.muradnajafli.newscatcher.domain.usecase.detail.AddSavedNewsUseCaseImpl
import com.muradnajafli.newscatcher.domain.usecase.detail.DeleteSavedNewsUseCase
import com.muradnajafli.newscatcher.domain.usecase.detail.DeleteSavedNewsUseCaseImpl
import com.muradnajafli.newscatcher.domain.usecase.detail.GetNewsByUrlUseCase
import com.muradnajafli.newscatcher.domain.usecase.detail.GetNewsByUrlUseCaseImpl
import com.muradnajafli.newscatcher.domain.usecase.dropdown.ReadLanguageUseCase
import com.muradnajafli.newscatcher.domain.usecase.dropdown.ReadLanguageUseCaseImpl
import com.muradnajafli.newscatcher.domain.usecase.dropdown.UpdateLanguageUseCase
import com.muradnajafli.newscatcher.domain.usecase.dropdown.UpdateLanguageUseCaseImpl
import com.muradnajafli.newscatcher.domain.usecase.save.GetSavedNewsUseCase
import com.muradnajafli.newscatcher.domain.usecase.save.GetSavedNewsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun getLatestHeadlinesUseCase(
        latestHeadlinesRepository: LatestHeadlinesRepository
    ): GetLatestHeadlinesUseCase {
        return GetLatestHeadlinesUseCaseImpl(latestHeadlinesRepository)
    }

    @Provides
    @ViewModelScoped
    fun deleteSavedNewsUseCase(
        savedNewsRepository: SavedNewsRepository
    ): DeleteSavedNewsUseCase {
        return DeleteSavedNewsUseCaseImpl(savedNewsRepository)
    }

    @Provides
    @ViewModelScoped
    fun addSavedNewsUseCase(
        savedNewsRepository: SavedNewsRepository
    ): AddSavedNewsUseCase {
        return AddSavedNewsUseCaseImpl(savedNewsRepository)
    }

    @Provides
    @ViewModelScoped
    fun getNewsFromSearchUseCase(
        searchRepository: SearchNewsRepository
    ): GetNewsFromSearchUseCase {
        return GetNewsFromSearchUseCaseImpl(searchRepository)
    }

    @Provides
    @ViewModelScoped
    fun getMovieDetailsUseCase(
        repository: SavedNewsRepository
    ): GetSavedNewsUseCase {
        return GetSavedNewsUseCaseImpl(repository)
    }

    @Provides
    @ViewModelScoped
    fun getNewsByUrlUseCase(
        repository: SavedNewsRepository
    ): GetNewsByUrlUseCase {
        return GetNewsByUrlUseCaseImpl(repository)
    }

    @Provides
    @ViewModelScoped
    fun readLanguageUseCase(
        repository: DataStoreRepository
    ): ReadLanguageUseCase {
        return ReadLanguageUseCaseImpl(repository)
    }

    @Provides
    @ViewModelScoped
    fun updateLanguageUseCase(
        repository: DataStoreRepository
    ): UpdateLanguageUseCase {
        return UpdateLanguageUseCaseImpl(repository)
    }

}