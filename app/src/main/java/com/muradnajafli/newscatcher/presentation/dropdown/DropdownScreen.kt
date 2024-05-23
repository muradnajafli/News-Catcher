package com.muradnajafli.newscatcher.presentation.dropdown

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.muradnajafli.newscatcher.presentation.common.NavigateBackButton
import com.muradnajafli.newscatcher.presentation.dropdown.components.LanguageList

@Composable
fun DropdownScreen(
    onEvent: (DropDownUiEvents) -> Unit,
    onSetApplicationLocale: (String) -> Unit,
    navigateToBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        NavigateBackButton(
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
                .clip(CircleShape)
                .fillMaxWidth(0.20f)
                .clickable { navigateToBack() }
                .background(Color(0xFFD7D7D7).copy(alpha = 0.6f))
                .padding(4.dp)
        )

        LanguageList(
            onEvent = onEvent,
            onSetApplicationLocale = onSetApplicationLocale,
            navigateToBack = navigateToBack
        )

    }
}