package com.muradnajafli.newscatcher.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muradnajafli.newscatcher.domain.model.Article
import com.muradnajafli.newscatcher.domain.usecase.detail.AddSavedNewsUseCase
import com.muradnajafli.newscatcher.domain.usecase.detail.DeleteSavedNewsUseCase
import com.muradnajafli.newscatcher.domain.usecase.detail.GetNewsByUrlUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val addSavedNewsUseCase: AddSavedNewsUseCase,
    private val deleteSavedNewsUseCase: DeleteSavedNewsUseCase,
    private val getNewsByUrlUseCase: GetNewsByUrlUseCase
): ViewModel() {

    private val _isSaved: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isSaved = _isSaved.asStateFlow()

    fun checkIfNewsIsInSaved(link: String?) {
        viewModelScope.launch {
            val news = getNewsByUrlUseCase.getNewsByUrl(link)
            _isSaved.value = news != null
        }
    }

    fun addOrRemoveNewsFromSaved(article: Article, isChecked: Boolean) {
        if (isChecked) {
            addNewsToSaved(article)
        } else {
            deleteNewsFromSaved(article)
        }
    }

    private fun addNewsToSaved(article: Article) {
        viewModelScope.launch {
            addSavedNewsUseCase(article)
            _isSaved.value = true
        }
    }

    private fun deleteNewsFromSaved(article: Article) {
        viewModelScope.launch {
            deleteSavedNewsUseCase(article)
            _isSaved.value = false
        }
    }

}