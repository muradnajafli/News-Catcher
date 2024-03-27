package com.muradnajafli.newscatcher.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muradnajafli.newscatcher.domain.model.Article
import com.muradnajafli.newscatcher.presentation.home.components.ImageSlider
import com.muradnajafli.newscatcher.presentation.home.components.SearchPanel
import com.muradnajafli.newscatcher.presentation.home.components.SettingsColumn
import com.muradnajafli.newscatcher.util.UiText

@Composable
fun HomeScreen(
    searchState: SearchState,
    onHomeEvent: (HomeEvent) -> Unit,
    latestHeadlines: List<Article?>,
    searchResults: List<Article?>,
    navigateToDetails: (Article) -> Unit,
    navigateToDropdown: () -> Unit,
    appLanguage: String,
    errorHomeMessage: UiText?
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        SettingsColumn(
            navigateToDropdown = navigateToDropdown,
            appLanguage = appLanguage
        )

        if (errorHomeMessage != null) {
            Text(
                text = errorHomeMessage.asString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                color = Color.Red,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold
            )
        } else {
            ImageSlider(
                newsImages = latestHeadlines,
                onClick = navigateToDetails
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        SearchPanel(
            searchText = searchState.searchText,
            onSearchChange = { text ->
                onHomeEvent(HomeEvent.OnSearchTextChanged(text))
            },
            isSearching = searchState.isSearching,
            searchResults = searchResults,
            navigateToDetails = navigateToDetails,
            appLanguage = appLanguage
        )

    }
}