package com.muradnajafli.newscatcher.presentation.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muradnajafli.newscatcher.R

@Composable
fun NavigateBackButton(
    modifier: Modifier = Modifier,
    title: String? = "",
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow),
            contentDescription = "Arrow",
            tint = Color.Unspecified,
            modifier = Modifier
                .size(30.dp)
                .padding(start = 8.dp, end = 4.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = title ?: "",
            fontSize = 12.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}