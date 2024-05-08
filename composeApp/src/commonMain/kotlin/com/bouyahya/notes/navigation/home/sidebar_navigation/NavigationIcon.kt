package com.bouyahya.notes.navigation.home.sidebar_navigation

import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.bouyahya.notes.navigation.home.utils.NavigationItem

@Composable
fun NavigationIcon(
    item: NavigationItem,
    selected: Boolean
) {
    BadgedBox(badge = {
        if (item.badgeCount != null) {
            Badge {
                Text(text = item.badgeCount.toString())
            }
        } else if (item.hasNews) {
            Badge()
        }
    }) {
        Icon(
            imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
            contentDescription = item.title,
        )
    }
}