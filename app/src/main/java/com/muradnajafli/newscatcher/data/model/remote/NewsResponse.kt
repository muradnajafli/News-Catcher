package com.muradnajafli.newscatcher.data.model.remote


import com.google.gson.annotations.SerializedName
import com.muradnajafli.newscatcher.domain.model.Article

data class NewsResponse(
    @SerializedName("articles")
    val articles: List<Article?>
//    @SerializedName("page")
//    val page: Int?,
//    @SerializedName("page_size")
//    val pageSize: Int?,
//    @SerializedName("status")
//    val status: String?,
//    @SerializedName("total_hits")
//    val totalHits: Int?
)