package com.muradnajafli.newscatcher.data.retrofit

import com.muradnajafli.newscatcher.data.model.remote.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsApi {

    @GET("search")
    suspend fun getSearchResponse(
        @Query("q") query: String
    ): Response<NewsResponse>

    @GET("latest_headlines")
    suspend fun getLatestHeadlines(): Response<NewsResponse>

}