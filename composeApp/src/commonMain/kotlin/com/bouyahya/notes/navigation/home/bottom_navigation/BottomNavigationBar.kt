package com.bouyahya.notes.navigation.home.bottom_navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.contentColorFor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bouyahya.notes.navigation.home.utils.NavigationItem
import com.bouyahya.notes.navigation.home.utils.selected

@Composable
fun BottomNavigationBar(
    items: List<NavigationItem>,
    navController: NavController,
) {
    BottomNavigation(
        modifier = Modifier.fillMaxWidth().windowInsetsPadding(WindowInsets.ime),
        backgroundColor = MaterialTheme.colorScheme.background,
        contentColor = contentColorFor(androidx.compose.material.MaterialTheme.colors.secondaryVariant),
        elevation = 8.dp
    ) {
        items.map { navigationItem ->
            val selected = navigationItem.selected(navController)

            BottomNavItem(
                navigationItem = navigationItem,
                selected = selected,
                onTabChanged = {
                    navController.navigate(navigationItem.route) {
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}