package com.muradnajafli.newscatcher.presentation.dropdown

sealed class DropDownUiEvents {
    data class OnSetLanguage(val language: String) : DropDownUiEvents()
}