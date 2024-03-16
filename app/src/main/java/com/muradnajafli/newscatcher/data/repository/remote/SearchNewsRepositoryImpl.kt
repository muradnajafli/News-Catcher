package com.muradnajafli.newscatcher.data.repository.remote

import com.muradnajafli.newscatcher.data.retrofit.NewsApi
import com.muradnajafli.newscatcher.domain.repository.remote.SearchNewsRepository
import javax.inject.Inject

class SearchNewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
) : SearchNewsRepository {

    override suspend fun searchNews(query: String) =
        newsApi.getSearchResponse(query)

}