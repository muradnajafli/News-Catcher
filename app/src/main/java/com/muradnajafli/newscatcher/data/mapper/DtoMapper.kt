package com.muradnajafli.newscatcher.data.mapper

import com.muradnajafli.newscatcher.data.model.remote.ArticleDto
import com.muradnajafli.newscatcher.domain.model.Article
import com.muradnajafli.newscatcher.utils.DateUtils

object DtoMapper {
    fun ArticleDto.toArticle(): Article {
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
}