package com.muradnajafli.newscatcher.domain.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.muradnajafli.newscatcher.utils.DateUtils
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "saved_articles")
data class Article(
    @SerializedName("author")
    val author: String?,
    @SerializedName("excerpt")
    val excerpt: String?,
    @PrimaryKey val link: String,
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
): Parcelable {
    val formattedDate: String
        get() = publishedDate?.let { DateUtils.formatDateTime(it) } ?: ""
}