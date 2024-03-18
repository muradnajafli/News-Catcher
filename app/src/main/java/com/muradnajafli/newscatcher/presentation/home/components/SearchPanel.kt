package com.muradnajafli.newscatcher.presentation.home.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.muradnajafli.newscatcher.R
import com.muradnajafli.newscatcher.domain.model.Article
import com.muradnajafli.newscatcher.presentation.common.ArticleItem

@Composable
fun SearchPanel(
    searchText: String = "",
    onSearchChange: (String) -> Unit,
    isSearching: Boolean,
    searchResults: List<Article?>,
    navigateToDetails: (Article) -> Unit,
    errorSearchMessage: String?
) {
    val textFieldColors = TextFieldDefaults.colors(
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        focusedTextColor = Color.Black,
        unfocusedTextColor = Color.Black
    )
    TextField(
        value = searchText,
        onValueChange = onSearchChange,
        placeholder = {
            Text(
                text = stringResource(id = R.string.search_placeholder)
            )
        },
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .padding(16.dp)
            .border(
                1.dp, Color(0xFFD7D7D7),
                RoundedCornerShape(16.dp)
            ),
        colors = textFieldColors,
        singleLine = true
    )

    Spacer(modifier = Modifier.height(16.dp))

    if (isSearching) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    } else {
        LazyColumn {
            items(searchResults.filterNotNull()) { article ->
                Spacer(modifier = Modifier.height(16.dp))
                ArticleItem(
                    article = article,
                    onClick = { navigateToDetails(article) }
                )
            }
        }
    }

}