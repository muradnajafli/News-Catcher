package com.muradnajafli.newscatcher.presentation.detail

import com.muradnajafli.newscatcher.domain.model.Article

sealed class DetailsUiEvents {
    data class OnAddOrDeleteFromSaved(val article: Article, val isAddOperation: Boolean) : DetailsUiEvents()
    data class OnCheckIfNewsIsInSaved(val articleLink: String) : DetailsUiEvents()
}
