package com.muradnajafli.newscatcher.presentation.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.muradnajafli.newscatcher.domain.model.Article
import com.muradnajafli.newscatcher.presentation.common.ArticleItem
import com.muradnajafli.newscatcher.util.DateUtils
import com.muradnajafli.newscatcher.util.LanguageUtil

@Composable
fun BookMarkScreen(
    articles: List<Article?>,
    navigateToDetails: (Article) -> Unit,
    languageUtil: LanguageUtil
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 72.dp)
    ) {
        Text(
            text = DateUtils.getTodayDate(
                when (languageUtil.getApplicationLocale()) {
                    "ru" -> "ru"
                    else -> "en"
                }
            ),
            color = Color(0xFF89969C),
            modifier = Modifier.padding(start = 16.dp)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(
                articles, key = { article -> article!!.link }
            ) { article ->
                Spacer(modifier = Modifier.height(16.dp))
                if (article != null) {
                    ArticleItem(
                        article = article,
                        onClick = { navigateToDetails(article) }
                    )
                }
            }
        }
    }
}