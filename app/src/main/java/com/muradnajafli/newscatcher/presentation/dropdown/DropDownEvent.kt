package com.muradnajafli.newscatcher.presentation.dropdown

sealed class DropDownEvent {
    data class OnSetLanguage(val language: String) : DropDownEvent()
}