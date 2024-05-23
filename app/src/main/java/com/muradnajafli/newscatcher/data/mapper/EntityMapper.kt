package com.muradnajafli.newscatcher.data.mapper

import com.muradnajafli.newscatcher.data.model.local.ArticleEntity
import com.muradnajafli.newscatcher.domain.model.Article
import com.muradnajafli.newscatcher.utils.DateUtils

object EntityMapper {
    fun ArticleEntity?.toArticle(): Article? {
        if (this == null) return null

        return Article(
            author = author ?: "",
            excerpt = excerpt ?: "",
            media = media ?: "",
            publishedDate = publishedDate?.let { DateUtils.formatDateTime(it) } ?: "",
            summary = summary ?: "",
            title = title ?: "",
            topic = topic ?: "",
            link = link
        )
    }

    fun Article.toEntity(): ArticleEntity {
        return ArticleEntity(
            author = author,
            excerpt = excerpt,
            media = media,
            publishedDate = publishedDate,
            summary = summary,
            title = title,
            topic = topic,
            link = link
        )
    }
}