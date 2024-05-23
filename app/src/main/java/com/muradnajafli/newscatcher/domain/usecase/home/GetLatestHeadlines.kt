package com.muradnajafli.newscatcher.domain.usecase.home

import com.muradnajafli.newscatcher.domain.model.Article
import com.muradnajafli.newscatcher.domain.repository.remote.LatestHeadlinesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetLatestHeadlinesUseCaseImpl @Inject constructor(
    private val repository: LatestHeadlinesRepository
) : GetLatestHeadlinesUseCase {

    override suspend operator fun invoke(language: String): List<Article> =
        withContext(Dispatchers.IO) {
            repository.getLatestHeadlines(language)
        }
}

interface GetLatestHeadlinesUseCase {
    suspend operator fun invoke(language: String): List<Article>
}