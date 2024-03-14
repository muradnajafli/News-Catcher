package com.muradnajafli.newscatcher.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muradnajafli.newscatcher.R


@Composable
@Preview(showBackground = true)
fun ImageViewer() {
    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(newsImages) { item ->
            val isFirst = item == newsImages.first()
            val isLast = item == newsImages.last()

            Spacer(
                modifier = Modifier
                    .width(if (isFirst || isLast) 16.dp else 8.dp))

            HomeImageItem(
                topic = item.topic,
                title = item.title,
                image = item.image
            )
        }
    }
}


@Composable
fun HomeImageItem(
    topic: String,
    title: String,
    image: Int
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .height(180.dp)
            .width(320.12.dp),
        contentAlignment = Alignment.TopStart
    ) {

        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(id = image),
            contentDescription = "News Image"
        )
        
        Box(
            modifier = Modifier
                .padding(16.dp)
                .clip(CircleShape)
                .alpha(0.6f)
                .background(Color(0xFFD7D7D7))
                .width(86.dp)
                .height(22.dp)
            ,
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = topic,
                fontWeight = FontWeight.Medium,
                fontSize = 11.sp
            )
        }

        Text(
            text = title,
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

data class NewsImageItem(
    val title: String,
    val topic: String,
    val image: Int
)

private val newsImages = listOf(
    NewsImageItem(
        topic = "Topic",
        title = "New Music Releases March 25: Ed Sheeran, J Balvin, Marendrandom",
        image = R.drawable.img
    ),
    NewsImageItem(
        topic = "Topic",
        title = "New Music Releases March 25: Ed Sheeran, J Balvin, Marendrandom",
        image = R.drawable.img
    ),
    NewsImageItem(
        topic = "Topic",
        title = "New Music Releases March 25: Ed Sheeran, J Balvin, Marendrandom",
        image = R.drawable.img
    ),
)