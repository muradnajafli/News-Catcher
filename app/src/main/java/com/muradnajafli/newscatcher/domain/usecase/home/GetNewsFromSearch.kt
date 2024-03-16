package com.muradnajafli.newscatcher.domain.usecase.home

import com.muradnajafli.newscatcher.data.model.remote.NewsResponse
import com.muradnajafli.newscatcher.domain.repository.remote.SearchNewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class GetNewsFromSearchUseCaseImpl @Inject constructor(
    private val repository: SearchNewsRepository
) : GetNewsFromSearchUseCase {

    override suspend operator fun invoke(query: String) =
        withContext(Dispatchers.IO) {
            repository.searchNews(query)
        }

    }

interface GetNewsFromSearchUseCase {
    suspend operator fun invoke(query: String): Response<NewsResponse>
}