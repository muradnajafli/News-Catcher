package com.muradnajafli.newscatcher.presentation.dropdown.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muradnajafli.newscatcher.presentation.dropdown.DropDownUiEvents

@Composable
fun LanguageList(
    modifier: Modifier = Modifier,
    languageItems: List<String> = languages,
    onEvent: (DropDownUiEvents) -> Unit,
    onSetApplicationLocale: (String) -> Unit,
    navigateToBack: () -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        items(languageItems) { language ->
            LanguageItem(
                language = language,
                setLanguage = {
                    onEvent(DropDownUiEvents.OnSetLanguage(it))
                    onSetApplicationLocale(it)
                    navigateToBack()
                }
            )
            HorizontalDivider(color = Color.Black, thickness = 1.dp)
        }
    }
}

@Composable
fun LanguageItem(
    language: String,
    setLanguage: (String) -> Unit
) {
    Text(
        text = language,
        color = Color.Black,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                setLanguage(language)
            }
            .padding(
                start = 32.dp,
                end = 16.dp,
                top = 16.dp,
                bottom = 16.dp
            )
        ,
        textAlign = TextAlign.Start,
        fontSize = 18.sp,
        fontWeight = FontWeight.W500
    )
}

private val languages = listOf(
    "EN",
    "RU"
)