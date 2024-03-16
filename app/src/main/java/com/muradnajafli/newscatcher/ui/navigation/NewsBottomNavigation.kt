package com.muradnajafli.newscatcher.ui.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muradnajafli.newscatcher.R

@Composable
fun NewsBottomNavigation(
    items: List<BottomNavItem>,
    selectedItem: String,
    onItemClick: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        color = Color.White
    ) {
        HorizontalDivider(color = Color.Black, thickness = 1.dp)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { item ->
                BottomNavItem(
                    item = item,
                    isSelected = item.content == selectedItem,
                    onClick = { onItemClick(item.content) }
                )
            }
        }
    }
}

@Composable
private fun BottomNavItem(
    item: BottomNavItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val color = if (isSelected) Color.Black else Color.Black

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable { onClick() }
    ) {
        Icon(
            painter = painterResource(id = if (isSelected) item.selectedIcon else item.icon),
            contentDescription = item.content,
            tint = color
        )
    }
}

data class BottomNavItem(
    val icon: Int,
    val selectedIcon: Int,
    val content: String
)

@Composable
@Preview(showBackground = true)
fun CustomBottomNavigationPreview() {
    val items = listOf(
        BottomNavItem(
            icon = R.drawable.ic_home,
            selectedIcon = R.drawable.ic_filled_home,
            content = "Home"
        ),
        BottomNavItem(
            icon = R.drawable.ic_save,
            selectedIcon = R.drawable.ic_filled_save,
            content = "Bookmark"
        )
    )

    var selectedItem by remember { mutableStateOf("Home") }

    NewsBottomNavigation(
        items = items,
        selectedItem = selectedItem,
        onItemClick = { selectedItem = it }
    )
}
