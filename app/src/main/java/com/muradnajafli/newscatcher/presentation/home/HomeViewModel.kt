package com.muradnajafli.newscatcher.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muradnajafli.newscatcher.domain.model.Article
import com.muradnajafli.newscatcher.domain.usecase.home.GetLatestHeadlinesUseCase
import com.muradnajafli.newscatcher.domain.usecase.home.GetNewsFromSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getLatestHeadlinesUseCase: GetLatestHeadlinesUseCase,
    private val getNewsFromSearchUseCase: GetNewsFromSearchUseCase
) : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _latestHeadlines = MutableStateFlow(listOf<Article?>())
    val latestHeadlines = _latestHeadlines.asStateFlow()

    private val _searchResults = MutableStateFlow(listOf<Article?>())
    val searchResults = _searchResults.asStateFlow()

    init {
        getLatestHeadlines()
    }

    fun searchArticles(text: String) {
        viewModelScope.launch {
            _isSearching.value = true
            try {
                _searchText.value = text
                val response = getNewsFromSearchUseCase(text)
                if (response.isSuccessful) {
                    _searchResults.value = response.body()?.articles ?: emptyList()
                } else {
                    _searchResults.value = emptyList()
                }
            } finally {
                _isSearching.value = false
            }
        }
    }

    private fun getLatestHeadlines() {
        viewModelScope.launch {
            val response = getLatestHeadlinesUseCase()
            if (response.isSuccessful) {
                _latestHeadlines.value = response.body()?.articles ?: emptyList()
            } else {
                _latestHeadlines.value = emptyList()
            }
        }
    }



}