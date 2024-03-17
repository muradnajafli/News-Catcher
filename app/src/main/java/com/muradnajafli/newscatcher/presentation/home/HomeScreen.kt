package com.muradnajafli.newscatcher.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.muradnajafli.newscatcher.domain.model.Article
import com.muradnajafli.newscatcher.presentation.home.components.ImageSlider
import com.muradnajafli.newscatcher.presentation.home.components.SearchPanel
import com.muradnajafli.newscatcher.presentation.home.components.SettingsColumn

@Composable
fun HomeScreen(
    searchState: SearchState,
    latestHeadlines: List<Article?>,
    searchResults: List<Article?>,
    navigateToDetails: (Article) -> Unit,
    navigateToDropdown: () -> Unit,
    appLanguage: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 48.dp)
    ) {
        SettingsColumn(
            navigateToDropdown = navigateToDropdown,
            appLanguage = appLanguage
        )

        ImageSlider(
            newsImages = latestHeadlines,
            onClick = navigateToDetails
        )

        SearchPanel(
            searchText = searchState.searchText,
            onSearchChange = searchState.onSearchChange,
            isSearching = searchState.isSearching,
            searchResults = searchResults,
            navigateToDetails = navigateToDetails
        )
    }
}