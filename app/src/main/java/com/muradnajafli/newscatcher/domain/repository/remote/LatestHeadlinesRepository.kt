package com.muradnajafli.newscatcher.domain.repository.remote

import com.muradnajafli.newscatcher.domain.model.Article

interface LatestHeadlinesRepository {
    suspend fun getLatestHeadlines(language: String): List<Article>
}