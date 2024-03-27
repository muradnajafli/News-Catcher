package com.muradnajafli.newscatcher.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muradnajafli.newscatcher.R
import com.muradnajafli.newscatcher.domain.model.Article
import com.muradnajafli.newscatcher.domain.usecase.dropdown.ReadLanguageUseCase
import com.muradnajafli.newscatcher.domain.usecase.home.GetLatestHeadlinesUseCase
import com.muradnajafli.newscatcher.domain.usecase.home.GetNewsFromSearchUseCase
import com.muradnajafli.newscatcher.util.InternetChecker
import com.muradnajafli.newscatcher.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getLatestHeadlinesUseCase: GetLatestHeadlinesUseCase,
    private val getNewsFromSearchUseCase: GetNewsFromSearchUseCase,
    private val internetChecker: InternetChecker,
    private val readLanguageUseCase: ReadLanguageUseCase
) : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _latestHeadlines = MutableStateFlow(listOf<Article?>())
    val latestHeadlines = _latestHeadlines.asStateFlow()

    private val _searchResults = MutableStateFlow(listOf<Article?>())
    val searchResults = _searchResults.asStateFlow()

    private val _errorHomeMessage = MutableStateFlow<UiText?>(null)
    val errorHomeMessage = _errorHomeMessage.asStateFlow()

    private val _errorSearchMessage = MutableStateFlow<UiText?>(null)

    init {
        observeLanguageChanges()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnSearchTextChanged -> searchArticles(event.searchText)
        }
    }

    private fun observeLanguageChanges() {
        viewModelScope.launch {
            readLanguageUseCase().collect { language ->
                fetchLatestHeadlines(language)
            }
        }
    }

    fun searchArticles(text: String) {
        viewModelScope.launch {
            _isSearching.value = true
            _searchText.value = text
            try {
                if (!internetChecker.isInternetAvailable()) {
                    _isSearching.value = false
                    _errorSearchMessage.value = UiText.StringResource(
                        R.string.no_internet_error
                    )
                    return@launch
                }

                val response = getNewsFromSearchUseCase(text)
                if (response.isSuccessful) {
                    _searchResults.value = response.body()?.articles ?: emptyList()
                } else {
                    _errorSearchMessage.value = UiText.DynamicString(response.message())
                }
            } catch (e: Exception) {
                _searchResults.value = emptyList()
                _errorSearchMessage.value = e.message?.let { UiText.DynamicString(it) }
            } finally {
                _isSearching.value = false
            }
        }
    }

    private fun fetchLatestHeadlines(language: String) {
        viewModelScope.launch {
            try {
                if (!internetChecker.isInternetAvailable()) {
                    _latestHeadlines.value = emptyList()
                    _errorHomeMessage.value = UiText.StringResource(
                        R.string.no_internet_error
                    )
                    return@launch
                }

                val response = getLatestHeadlinesUseCase(language)
                if (response.isSuccessful) {
                    _latestHeadlines.value = response.body()?.articles ?: emptyList()
                } else {
                    _latestHeadlines.value = emptyList()
                    _errorHomeMessage.value = UiText.DynamicString(response.message())
                }
            } catch (e: Exception) {
                _errorHomeMessage.value = e.message?.let { UiText.DynamicString(it) }
            }
        }
    }

}