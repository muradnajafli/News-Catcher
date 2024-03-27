package com.muradnajafli.newscatcher.presentation.detail

import com.muradnajafli.newscatcher.domain.model.Article

sealed class DetailsEvent {
    data class OnAddOrDeleteFromSaved(val article: Article, val isAddOperation: Boolean) : DetailsEvent()
    data class OnCheckIfNewsIsInSaved(val articleLink: String) : DetailsEvent()
}
