package com.muradnajafli.newscatcher.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.muradnajafli.newscatcher.domain.model.Article
import com.muradnajafli.newscatcher.presentation.detail.components.ArticleTextContent
import com.muradnajafli.newscatcher.presentation.detail.components.MoreButton
import com.muradnajafli.newscatcher.presentation.common.NavigateBackButton

@Composable
fun DetailsScreen(
    article: Article,
    isSaved: Boolean,
    onEvent: (DetailsUiEvents) -> Unit,
    onNavigateBack: () -> Unit
) {

    LaunchedEffect(key1 = article.link) {
        onEvent(DetailsUiEvents.OnCheckIfNewsIsInSaved(article.link))
    }

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.TopStart
    ) {
        AsyncImage(
            model = article.media,
            contentDescription = "Image",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
        )

        NavigateBackButton(
            title = article.title,
            modifier = Modifier
                .padding(
                    top = 16.dp,
                    start = 16.dp
                )
                .clip(CircleShape)
                .fillMaxWidth(0.33f)
                .clickable {
                    onNavigateBack()
                }
                .background(Color(0xFFD7D7D7).copy(alpha = 0.6f))
                .padding(
                    start = 4.dp,
                    end = 4.dp
                )
        )

        MoreButton(
            context = context,
            coroutineScope = coroutineScope,
            link = article.link,
            onSaveButtonClicked = {
                onEvent(DetailsUiEvents.OnAddOrDeleteFromSaved(article, !isSaved))
            },
            isSaved = isSaved
        )

        ArticleTextContent(
            article = article,
            coroutineScope = coroutineScope,
            context = context
        )
    }
}