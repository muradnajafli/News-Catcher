package com.muradnajafli.newscatcher.domain.usecase.home

import com.muradnajafli.newscatcher.domain.model.Article
import com.muradnajafli.newscatcher.domain.repository.remote.SearchNewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetNewsFromSearchUseCaseImpl @Inject constructor(
    private val repository: SearchNewsRepository
) : GetNewsFromSearchUseCase {

    override suspend operator fun invoke(query: String): List<Article> =
        withContext(Dispatchers.IO) {
            repository.searchNews(query)
        }

    }

interface GetNewsFromSearchUseCase {
    suspend operator fun invoke(query: String): List<Article>
}