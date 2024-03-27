package com.muradnajafli.newscatcher.domain.usecase.dropdown

import com.muradnajafli.newscatcher.domain.repository.local.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class ReadLanguageUseCaseImpl @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : ReadLanguageUseCase {

    override fun invoke(): Flow<String> {
        return dataStoreRepository.readLanguage()
    }

}

interface ReadLanguageUseCase {
    operator fun invoke(): Flow<String>
}