package com.muradnajafli.newscatcher.data.retrofit

import com.muradnajafli.newscatcher.data.model.remote.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Locale

interface NewsApi {

    @GET("search")
    suspend fun getSearchResponse(
        @Query("q") query: String,
        @Query("lang") lang: String = getLanguageParam()
    ): Response<NewsResponse>

    @GET("latest_headlines")
    suspend fun getLatestHeadlines(
        @Query("lang") lang: String = getLanguageParam()
    ): Response<NewsResponse>

    companion object {
        private fun getLanguageParam(): String {
            val currentLanguage = Locale.getDefault().language
            return when (currentLanguage) {
                "ru" -> "ru"
                else -> "en"
            }
        }
    }

}