package com.muradnajafli.newscatcher.ui.detail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.muradnajafli.newscatcher.R
import com.muradnajafli.newscatcher.domain.model.Article
import com.muradnajafli.newscatcher.ui.detail.components.MoreButton
import kotlinx.coroutines.launch
import java.util.Locale

@Composable
fun DetailsScreen(
    article: Article,
    addOrDeleteFromSaved: (Article, Boolean) -> Unit,
    checkIfNewsIsInSaved: (String?) -> Unit,
    isSaved: Boolean,
    navigateToBack: () -> Unit
) {

    LaunchedEffect(key1 = article.link) {
        checkIfNewsIsInSaved(article.link)
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

        Row(
            modifier = Modifier
                .padding(
                    top = 16.dp,
                    start = 16.dp
                )
                .clip(CircleShape)
                .fillMaxWidth(0.33f)
                .height(35.dp)
                .clickable {
                    navigateToBack()
                }
                .background(Color(0xFFD7D7D7).copy(alpha = 0.6f))
                .padding(
                    start = 4.dp,
                    end = 4.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow),
                contentDescription = "Arrow",
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(30.dp)
                    .padding(
                        start = 8.dp,
                        end = 4.dp
                    )
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = article.title ?: "",
                fontSize = 12.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }

        MoreButton(
            context = context,
            coroutineScope = coroutineScope,
            link = article.link,
            onSaveButtonClicked = {
                addOrDeleteFromSaved(article, !isSaved)
            },
            isSaved = isSaved
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 190.dp
                )
                .clip(
                    shape = RoundedCornerShape(
                        topStart = 32.dp,
                        topEnd = 32.dp
                    )
                )
                .background(Color.White)
                .padding(24.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = article.topic?.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(Locale.ENGLISH) else it.toString()
                } ?: "",
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color(0xFFD7D7D7))
                    .padding(
                        top = 4.dp,
                        bottom = 4.dp,
                        start = 32.dp,
                        end = 32.dp
                    )
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = article.title ?: "",
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = article.author ?: "",
                fontSize = 14.sp,
                color = Color(0xFF0AA7FF),
                fontWeight = FontWeight.W700,
                modifier = Modifier
                    .align(Alignment.End)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = article.excerpt ?: "",
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight.W700
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = article.summary ?: "",
                fontSize = 14.sp,
                fontStyle = FontStyle.Normal,
                color = Color(0xFF89969C),
                fontWeight = FontWeight.W500
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Read more...",
                color = Color(0xFF57A5D1),
                fontWeight = FontWeight.W600,
                fontSize = 14.sp,
                modifier = Modifier
                    .align(Alignment.End)
                    .clickable {
                        article.link.let { url ->
                            coroutineScope.launch {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                context.startActivity(intent)
                            }
                        }
                    }
            )

        }

    }
}

//@Composable
//@Preview(showBackground = true)
//fun DetailsScreenPreview() {
//    DetailsScreen(
//        topic = "Topic",
//        title = "Factbox: Who is still buying Russian crude oil?",
//        author = "Jennifer Wars",
//        excerpt = "Australia, Britain, Canada and the United States have imposed outright bans on Russian oil purchases following Moscow's invasion of Ukraine, but members of the European Union are split.",
//        summary = "(Reuters) - Australia, Britain, Canada and the United States have imposed outright bans on Russian oil purchases following Moscow's invasion of Ukraine, but members of the European Union are split.EU foreign ministers failed to agree on Monday on sanctioning Russian gas and oil supplies, which account for 40% and 27% of the bloc's total use of those commodities respectively.Germany, the EU's top user of Russian crude oil and the Netherlands, a key trading hub, argue that the EU couldn't cut its dependence on Russian supplies overnight.",
//        link = "https://www.reuters.com/business/energy/factbox-who-is-still-buying-russian-crude-oil-2022-03-01/",
//        media = "https://www.reuters.com/business/energy/factbox-who-is-still-buying-russian-crude-oil-2022-03-01/",
//        publishedDate = "2022-03-01",
//        onSaveButtonClicked = {
//            SavedNews(
//                tableId = 0,
//                topic = "Topic",
//                title = "Factbox: Who is still buying Russian crude oil?",
//                media = "https://www.reuters.com/business/energy/factbox-who-is-still-buying-russian-crude-oil-2022-03-01/",
//                publishedDate = "2022-03-01"
//            )
//        }
//    )
//}

//@Preview(showBackground = true)
//@Composable
//fun DetailsScreenPreview() {
//
//    DetailsScreen(
//        article = Article(
//            author = "Murad",
//            title = "Coinbase says Apple blocked its last app release on NFTs in Wallet ... - CryptoSaurus",
//            topic = "Coinbase",
//            excerpt = "Coinbase says Apple blocked its last app release on NFTs in Wallet ... - CryptoSaurus",
//            summary = "Coinbase says Apple blocked its last app release on NFTs in Wallet ... - CryptoSaurus",
//            publishedDate = "2023-06-16T22:24:33Z",
//            link = "https://consent.google.com/ml?continue=https://news.google.com/rss/articles/CBMiaWh0dHBzOi8vY3J5cHRvc2F1cnVzLnRlY2gvY29pbmJhc2Utc2F5cy1hcHBsZS1ibG9ja2VkLWl0cy1sYXN0LWFwcC1yZWxlYXNlLW9uLW5mdHMtaW4td2FsbGV0LXJldXRlcnMtY29tL9IBAA?oc%3D5&gl=FR&hl=en-US&cm=2&pc=n&src=1",
//            media = "https://media.wired.com/photos/6495d5e893ba5cd8bbdc95af/191:100/w_1280,c_limit/The-EU-Rules-Phone-Batteries-Must-Be-Replaceable-Gear-2BE6PRN.jpg"
//        )
//    ) {
//
//    }
//}