package com.muradnajafli.newscatcher.domain.repository.remote

import com.muradnajafli.newscatcher.data.model.remote.NewsResponse
import retrofit2.Response

interface LatestHeadlinesRepository {
    suspend fun getLatestHeadlines(language: String): Response<NewsResponse>
}