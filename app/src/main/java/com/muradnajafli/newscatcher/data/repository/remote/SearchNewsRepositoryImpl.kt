package com.muradnajafli.newscatcher.data.repository.remote

import com.muradnajafli.newscatcher.data.mapper.DtoMapper.toArticle
import com.muradnajafli.newscatcher.data.retrofit.NewsApi
import com.muradnajafli.newscatcher.domain.model.Article
import com.muradnajafli.newscatcher.domain.repository.remote.SearchNewsRepository
import javax.inject.Inject

class SearchNewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
) : SearchNewsRepository {

    override suspend fun searchNews(query: String): List<Article> {
        return try {
            val response = newsApi.getSearchResponse(query)
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