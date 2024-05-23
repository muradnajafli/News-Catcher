package com.muradnajafli.newscatcher.presentation.home

import com.muradnajafli.newscatcher.domain.model.Article
import com.muradnajafli.newscatcher.utils.UiText

data class HomeUiState(
    val latestHeadlines: List<Article?> = emptyList(),
    val searchResults: List<Article?> = emptyList(),
    val isSearching: Boolean = false,
    val searchText: String = "",
    val errorHomeMessage: UiText? = null,
    val errorSearchMessage: UiText? = null
)
