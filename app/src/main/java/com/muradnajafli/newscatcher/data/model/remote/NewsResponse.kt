package com.muradnajafli.newscatcher.data.model.remote


import com.google.gson.annotations.SerializedName

@JvmInline
value class NewsResponse(
    @SerializedName("articles")
    val articleDtoList: List<ArticleDto?>
//    @SerializedName("page")
//    val page: Int?,
//    @SerializedName("page_size")
//    val pageSize: Int?,
//    @SerializedName("status")
//    val status: String?,
//    @SerializedName("total_hits")
//    val totalHits: Int?
)