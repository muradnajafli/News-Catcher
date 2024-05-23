package com.muradnajafli.newscatcher.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.muradnajafli.newscatcher.R
import com.muradnajafli.newscatcher.domain.model.Article
import java.util.Locale


@Composable
fun ImageSlider(
    articles: List<Article?>,
    onClick: (Article) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        itemsIndexed(articles) { index, article ->
            if (article != null) {

                HomeImageItem(
                    article = article,
                    onClick = onClick
                )

                if (index != articles.lastIndex) {
                    Spacer(modifier = Modifier.width(8.dp))
                }

            }
        }
    }
}

@Composable
fun HomeImageItem(
    article: Article,
    onClick: (Article) -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .height(180.dp)
            .width(320.12.dp)
            .clickable {
                onClick(article)
            }
            .border(
                1.dp, Color(0xFFD7D7D7),
                RoundedCornerShape(16.dp)
            ),
        contentAlignment = Alignment.TopStart
    ) {

        AsyncImage(
            model = article.media,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds,
            contentDescription = "News Image",
            placeholder = painterResource(
                id = R.drawable.ic_image_placeholder
            )
        )

        Text(
            text = article.topic.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.ENGLISH) else it.toString()
            },
            fontWeight = FontWeight.W500,
            fontSize = 12.sp,
            modifier = Modifier
                .padding(16.dp)
                .clip(CircleShape)
                .background(Color(0xFFD7D7D7).copy(alpha = 0.6f))
                .padding(
                    top = 4.dp,
                    bottom = 4.dp,
                    start = 32.dp,
                    end = 32.dp
                )
        )

        Text(
            text = article.title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .background(Color.White.copy(alpha = 0.7f))
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 8.dp,
                    top = 8.dp
                )
        )
    }

}