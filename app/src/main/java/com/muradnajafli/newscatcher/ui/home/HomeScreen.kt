package com.muradnajafli.newscatcher.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.muradnajafli.newscatcher.ui.home.components.ImageViewer
import com.muradnajafli.newscatcher.ui.home.components.SearchPanel
import com.muradnajafli.newscatcher.ui.home.components.SettingsColumn
import com.muradnajafli.newscatcher.ui.home.components.TopBar

@Composable
fun HomeScreen(
    searchState: SearchState
) {

    Scaffold(
        topBar = {
            TopBar()
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            SettingsColumn()

            ImageViewer()

            SearchPanel(
                searchText = searchState.searchText,
                onSearchChange = searchState.onSearchChange,
                isSearching = searchState.isSearching,
                news = searchState.news
            )

        }
    }
    
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    HomeScreen(
        SearchState(
            searchText = "Search",
            onSearchChange = {},
            isSearching = false,
            news = emptyList()
        )
    )

}