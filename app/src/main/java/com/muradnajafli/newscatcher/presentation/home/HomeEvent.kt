package com.muradnajafli.newscatcher.presentation.home

sealed class HomeEvent {
    data class OnSearchTextChanged(val searchText: String) : HomeEvent()
}