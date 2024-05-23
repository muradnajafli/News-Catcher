package com.muradnajafli.newscatcher.data.model.remote

import com.google.gson.annotations.SerializedName

data class ArticleDto(
    @SerializedName("link")
    val link: String,
    @SerializedName("author")
    val author: String?,
    @SerializedName("excerpt")
    val excerpt: String?,
    @SerializedName("media")
    val media: String?,
    @SerializedName("published_date")
    val publishedDate: String?,
    @SerializedName("summary")
    val summary: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("topic")
    val topic: String?,
)