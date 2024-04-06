package com.muradnajafli.newscatcher.presentation.home

sealed class HomeUiEvents {
    data class OnSearchTextChanged(val searchText: String) : HomeUiEvents()
}