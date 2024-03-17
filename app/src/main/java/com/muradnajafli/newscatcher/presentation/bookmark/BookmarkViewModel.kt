package com.muradnajafli.newscatcher.presentation.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muradnajafli.newscatcher.domain.model.Article
import com.muradnajafli.newscatcher.domain.usecase.save.GetSavedNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val getSavedNewsUseCase: GetSavedNewsUseCase
) : ViewModel() {

    private val _savedNews = MutableStateFlow(listOf<Article?>())
    val savedNews = _savedNews.asStateFlow()

    init {
        getSavedNews()
    }

    private fun getSavedNews() {
        viewModelScope.launch {
            getSavedNewsUseCase().collect { articles ->
                _savedNews.value = articles ?: emptyList()
            }
        }
    }

}