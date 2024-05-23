package com.muradnajafli.newscatcher.domain.usecase.save

import com.muradnajafli.newscatcher.domain.model.Article
import com.muradnajafli.newscatcher.domain.repository.local.SavedNewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetSavedNewsUseCaseImpl @Inject constructor(
    private val repository: SavedNewsRepository
) : GetSavedNewsUseCase {

    override suspend operator fun invoke(): Flow<List<Article?>> =
        withContext(Dispatchers.IO) {
            repository.getSavedNews()
        }
}

interface GetSavedNewsUseCase {
    suspend operator fun invoke(): Flow<List<Article?>>
}