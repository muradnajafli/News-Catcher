package com.muradnajafli.newscatcher.domain.usecase.home

import com.muradnajafli.newscatcher.data.model.remote.NewsResponse
import com.muradnajafli.newscatcher.domain.repository.remote.LatestHeadlinesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class GetLatestHeadlinesUseCaseImpl @Inject constructor(
    private val repository: LatestHeadlinesRepository
) : GetLatestHeadlinesUseCase {

    override suspend operator fun invoke(language: String): Response<NewsResponse> =
        withContext(Dispatchers.IO) {
            repository.getLatestHeadlines(language)
        }
}

interface GetLatestHeadlinesUseCase {
    suspend operator fun invoke(language: String): Response<NewsResponse>
}