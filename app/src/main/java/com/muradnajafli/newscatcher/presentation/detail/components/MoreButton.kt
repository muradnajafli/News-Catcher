package com.muradnajafli.newscatcher.presentation.detail.components

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muradnajafli.newscatcher.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoxScope.MoreButton(
    context: Context,
    coroutineScope: CoroutineScope,
    link: String?,
    onSaveButtonClicked: () -> Unit,
    isSaved: Boolean
) {

    var isExpanded by rememberSaveable {
        mutableStateOf(false)
    }

    IconButton(
        onClick = {
            isExpanded = true
        },
        modifier = Modifier
            .align(Alignment.TopEnd)
            .padding(16.dp)
            .clip(CircleShape)
            .background(Color(0xFFC4C4C4).copy(alpha = 0.48f))
    ) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = "More",
            modifier = Modifier
                .align(Alignment.Center)
        )
        Surface(
            modifier = Modifier
                .padding(end = 46.dp, top = 36.dp)
        ) {
            DropdownMenuNoPaddingVertical(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false },
                modifier = Modifier
                    .background(Color(0xFFFFFEFE).copy(alpha = 0.95f))
            ) {
                DropdownMenuItem(
                    onClick = {
                        isExpanded = false
                        link?.let { linkValue ->
                            coroutineScope.launch {
                                val intent = Intent(Intent.ACTION_SEND)
                                intent.putExtra(Intent.EXTRA_TEXT, linkValue)
                                intent.type = "text/plain"
                                context.startActivity(Intent.createChooser(intent, "Share link"))
                            }
                        }
                    },
                    text = {
                        Text(
                            text = stringResource(id = R.string.share),
                            fontWeight = FontWeight.W500,
                            fontSize = 14.sp,
                            color = Color.Black
                        )
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_share),
                            contentDescription = "Share",
                            tint = Color.Unspecified
                        )
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )

                HorizontalDivider(color = Color.Black)

                DropdownMenuItem(
                    onClick = {
                        isExpanded = false
                        onSaveButtonClicked()
                    },
                    text = {
                        Text(
                            text = stringResource(id = if (isSaved) R.string.saved else R.string.save),
                            fontWeight = FontWeight.W500,
                            fontSize = 14.sp,
                            color = Color.Black
                        ) },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = if (isSaved) R.drawable.ic_filled_save else R.drawable.ic_save),
                            contentDescription = stringResource(id = if (isSaved) R.string.saved else R.string.save),
                            tint = Color.Unspecified
                        )
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}