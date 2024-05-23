package com.muradnajafli.newscatcher.domain.usecase.detail

import com.muradnajafli.newscatcher.domain.model.Article
import com.muradnajafli.newscatcher.domain.repository.local.SavedNewsRepository
import javax.inject.Inject

class GetNewsByUrlUseCaseImpl @Inject constructor(
    private val savedNewsRepository: SavedNewsRepository
) : GetNewsByUrlUseCase {

    override suspend fun getNewsByUrl(link: String): Article? {
        return savedNewsRepository.getNewsByUrl(link)
    }

}

interface GetNewsByUrlUseCase {
    suspend fun getNewsByUrl(link: String): Article?
}