package com.muradnajafli.newscatcher.domain.usecase.save

import com.muradnajafli.newscatcher.domain.model.Article
import com.muradnajafli.newscatcher.domain.repository.local.SavedNewsRepository
import javax.inject.Inject

class AddSavedNewsUseCaseImpl @Inject constructor(
    private val repository: SavedNewsRepository
) : AddSavedNewsUseCase {

    override suspend operator fun invoke(article: Article) {
        repository.saveNews(article)
    }
}

interface AddSavedNewsUseCase {
    suspend operator fun invoke(article: Article)
}