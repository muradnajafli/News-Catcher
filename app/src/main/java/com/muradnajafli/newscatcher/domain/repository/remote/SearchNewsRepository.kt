package com.muradnajafli.newscatcher.domain.repository.remote

import com.muradnajafli.newscatcher.data.model.remote.NewsResponse
import retrofit2.Response

interface SearchNewsRepository {
    suspend fun searchNews(query: String): Response<NewsResponse>
}