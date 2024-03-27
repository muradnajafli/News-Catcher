package com.muradnajafli.newscatcher.domain.usecase.dropdown

import com.muradnajafli.newscatcher.domain.repository.local.DataStoreRepository
import javax.inject.Inject


class UpdateLanguageUseCaseImpl @Inject constructor(
    private val dataStoreManager: DataStoreRepository
) : UpdateLanguageUseCase {

    override suspend operator fun invoke(language: String) {
        dataStoreManager.updateLanguage(language)
    }

}

interface UpdateLanguageUseCase {
    suspend operator fun invoke(language: String)
}