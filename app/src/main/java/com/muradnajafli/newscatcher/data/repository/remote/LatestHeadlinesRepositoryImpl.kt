package com.muradnajafli.newscatcher.data.repository.remote

import com.muradnajafli.newscatcher.data.retrofit.NewsApi
import com.muradnajafli.newscatcher.domain.repository.remote.LatestHeadlinesRepository
import javax.inject.Inject

class LatestHeadlinesRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
) : LatestHeadlinesRepository {

    override suspend fun getLatestHeadlines(language: String) = newsApi.getLatestHeadlines(language)

}