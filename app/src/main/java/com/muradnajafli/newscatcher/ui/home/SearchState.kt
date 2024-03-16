package com.muradnajafli.newscatcher.ui.home


data class SearchState(
    val searchText: String,
    val onSearchChange: (String) -> Unit,
    val isSearching: Boolean
)