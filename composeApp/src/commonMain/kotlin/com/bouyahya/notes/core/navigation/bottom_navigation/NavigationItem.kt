package com.bouyahya.notes.core.navigation.bottom_navigation

import androidx.compose.ui.graphics.vector.ImageVector
import cafe.adriel.voyager.navigator.tab.Tab

data class NavigationItem(
    val title: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
    val hasNews: Boolean = false,
    val badgeCount: Int? = null,
    val tab: Tab
)
