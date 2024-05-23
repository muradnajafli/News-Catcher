package com.muradnajafli.newscatcher.data.repository.remote

import com.muradnajafli.newscatcher.data.mapper.DtoMapper.toArticle
import com.muradnajafli.newscatcher.data.retrofit.NewsApi
import com.muradnajafli.newscatcher.domain.model.Article
import com.muradnajafli.newscatcher.domain.repository.remote.LatestHeadlinesRepository
import javax.inject.Inject

class LatestHeadlinesRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
) : LatestHeadlinesRepository {

    override suspend fun getLatestHeadlines(language: String): List<Article> {
        return try {
            val response = newsApi.getLatestHeadlines(language)
            if (response.isSuccessful) {
                response.body()?.articleDtoList?.mapNotNull {
                    it?.toArticle()
                } ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
