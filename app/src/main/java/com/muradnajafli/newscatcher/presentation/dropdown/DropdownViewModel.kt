package com.muradnajafli.newscatcher.presentation.dropdown

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muradnajafli.newscatcher.domain.usecase.dropdown.UpdateLanguageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DropdownViewModel @Inject constructor(
    private val updateLanguageUseCase: UpdateLanguageUseCase
): ViewModel() {

    fun onEvent(event: DropDownEvent) {
        when(event) {
            is DropDownEvent.OnSetLanguage -> updateLanguagePreference(event.language)
        }
    }

    private fun updateLanguagePreference(language: String) {
        viewModelScope.launch {
            updateLanguageUseCase(language)
        }
    }

}