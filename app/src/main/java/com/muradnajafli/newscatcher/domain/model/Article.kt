package com.muradnajafli.newscatcher.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Article(
    val author: String,
    val excerpt: String,
    val media: String,
    val publishedDate: String,
    val summary: String,
    val title: String,
    val topic: String,
    val link: String
): Parcelable
