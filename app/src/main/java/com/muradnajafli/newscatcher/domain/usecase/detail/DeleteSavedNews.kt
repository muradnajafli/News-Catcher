package com.muradnajafli.newscatcher.domain.usecase.detail

import com.muradnajafli.newscatcher.domain.model.Article
import com.muradnajafli.newscatcher.domain.repository.local.SavedNewsRepository
import javax.inject.Inject


class DeleteSavedNewsUseCaseImpl @Inject constructor(
    private val repository: SavedNewsRepository
) : DeleteSavedNewsUseCase {

    override suspend operator fun invoke(article: Article) {
        repository.deleteSavedNews(article)
    }
}

interface DeleteSavedNewsUseCase {
    suspend operator fun invoke(article: Article)
}