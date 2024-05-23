package com.muradnajafli.newscatcher.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muradnajafli.newscatcher.R
import com.muradnajafli.newscatcher.domain.usecase.dropdown.ReadLanguageUseCase
import com.muradnajafli.newscatcher.domain.usecase.home.GetLatestHeadlinesUseCase
import com.muradnajafli.newscatcher.domain.usecase.home.GetNewsFromSearchUseCase
import com.muradnajafli.newscatcher.utils.InternetChecker
import com.muradnajafli.newscatcher.utils.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getLatestHeadlinesUseCase: GetLatestHeadlinesUseCase,
    private val getNewsFromSearchUseCase: GetNewsFromSearchUseCase,
    private val internetChecker: InternetChecker,
    private val readLanguageUseCase: ReadLanguageUseCase
) : ViewModel() {

    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState = _homeUiState.asStateFlow()

    init {
        observeLanguageChanges()
    }

    fun onEvent(event: HomeUiEvents) {
        when (event) {
            is HomeUiEvents.OnSearchTextChanged -> searchArticles(event.searchText)
        }
    }

    private fun observeLanguageChanges() {
        viewModelScope.launch {
            readLanguageUseCase().collect { language ->
                fetchLatestHeadlines(language)
            }
        }
    }

    private fun searchArticles(text: String) {
        viewModelScope.launch {
            _homeUiState.update {
                it.copy(
                    isSearching = true,
                    searchText = text
                )
            }
            try {
                if (!internetChecker.isInternetAvailable()) {
                    _homeUiState.update {
                        it.copy(
                            isSearching = false,
                            errorHomeMessage = UiText.StringResource(
                                R.string.no_internet_error
                            )
                        )
                    }
                    return@launch
                }
                _homeUiState.update {
                    it.copy(
                        searchResults = getNewsFromSearchUseCase(text)
                    )
                }
            } catch (e: Exception) {
                _homeUiState.update {
                    it.copy(
                        searchResults = emptyList(),
                        errorSearchMessage = e.message?.let { errorMessage ->
                            UiText.DynamicString(errorMessage)
                        }
                    )
                }
            } finally {
                _homeUiState.update {
                    it.copy(
                        isSearching = false
                    )
                }
            }
        }
    }

    private fun fetchLatestHeadlines(language: String) {
        viewModelScope.launch {
            try {
                if (!internetChecker.isInternetAvailable()) {
                    _homeUiState.update {
                        it.copy(
                            latestHeadlines = emptyList(),
                            errorHomeMessage = UiText.StringResource(
                                R.string.no_internet_error
                            )
                        )
                    }
                    return@launch
                }
                _homeUiState.update {
                    it.copy(
                        latestHeadlines = getLatestHeadlinesUseCase(language)
                    )
                }
            } catch (e: Exception) {
                _homeUiState.update {
                    it.copy(
                        errorHomeMessage = e.message?.let { error ->
                            UiText.DynamicString(error)
                        }
                    )
                }
            }
        }
    }

}