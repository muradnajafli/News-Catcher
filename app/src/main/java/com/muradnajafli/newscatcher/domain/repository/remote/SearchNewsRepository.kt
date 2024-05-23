package com.muradnajafli.newscatcher.domain.repository.remote

import com.muradnajafli.newscatcher.domain.model.Article

interface SearchNewsRepository {
    suspend fun searchNews(query: String): List<Article>
}