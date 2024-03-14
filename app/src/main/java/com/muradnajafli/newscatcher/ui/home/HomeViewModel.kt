package com.muradnajafli.newscatcher.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muradnajafli.newscatcher.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@OptIn(FlowPreview::class)
@HiltViewModel
class HomeViewModel @Inject constructor(

) : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _news = MutableStateFlow(allNews)
    val news = searchText
        .debounce(500L)
        .onEach { _isSearching.update { true } }
        .combine(_news) { text, news ->
            if (text.isBlank()) {
                news
            } else {
                delay(200L)
                news.filter { it.doesMatchSearchQuery(text) }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _news.value
        )


    fun onSearchTextChanged(text: String) {
        _searchText.value = text
    }

}

data class News(
    val title: String,
    val topic: String,
    val image: Int,
    val author: String,
    val time: String
) {
    fun doesMatchSearchQuery(query: String): Boolean {

        val matchingCombinations = listOf(
            "${title}${topic}",
            "$title $topic",
            "${title.first()} ${time.first()}",
        )

        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}

private val allNews = listOf(
    News(
        topic = "Finance",
        title = "Factbox: Who is still buying Russian crude oil?",
        time = "2022-03-26",
        author = "Author",
        image = R.drawable.search_item
    ),
    News(
        topic = "Finance",
        title = "Factbox: Who is still buying Russian crude oil?",
        time = "2022-03-26",
        author = "Author",
        image = R.drawable.search_item
    ),
    News(
        topic = "Finance",
        title = "Factbox: Who is still buying Russian crude oil?",
        time = "2022-03-26",
        author = "Author",
        image = R.drawable.search_item
    ),
    News(
        topic = "Finance",
        title = "Factbox: Who is still buying Russian crude oil?",
        time = "2022-03-26",
        author = "Author",
        image = R.drawable.search_item
    ),
    News(
        topic = "Finance",
        title = "Factbox: Who is still buying Russian crude oil?",
        time = "2022-03-26",
        author = "Author",
        image = R.drawable.search_item
    )
)