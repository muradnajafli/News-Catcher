package com.muradnajafli.newscatcher.presentation.detail.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muradnajafli.newscatcher.R
import com.muradnajafli.newscatcher.domain.model.Article
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.Locale

@Composable
fun ArticleTextContent(
    article: Article?,
    coroutineScope: CoroutineScope,
    context: Context,
) {
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
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = article?.topic?.replaceFirstChar {
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
            text = article?.title ?: "",
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = article?.author ?: "",
            fontSize = 14.sp,
            color = Color(0xFF0AA7FF),
            fontWeight = FontWeight.W700,
            textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = article?.excerpt ?: "",
            fontSize = 14.sp,
            color = Color.Black,
            fontWeight = FontWeight.W700
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = article?.summary ?: "",
            fontSize = 14.sp,
            fontStyle = FontStyle.Normal,
            color = Color(0xFF89969C),
            fontWeight = FontWeight.W500
        )

        Spacer(modifier = Modifier.height(32.dp))


        Text(
            text = stringResource(id = R.string.read_more),
            color = Color(0xFF57A5D1),
            fontWeight = FontWeight.W600,
            fontSize = 14.sp,
            modifier = Modifier
                .align(Alignment.End)
                .clickable {
                    article?.link.let { url ->
                        coroutineScope.launch {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            context.startActivity(intent)
                        }
                    }
                }
        )
    }
}