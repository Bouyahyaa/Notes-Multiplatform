package com.bouyahya.notes.navigation.home.sidebar_navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bouyahya.notes.navigation.home.utils.NavigationItem
import com.bouyahya.notes.navigation.home.utils.selected

@Composable
fun NavigationSideBar(
    items: List<NavigationItem>,
    navController: NavHostController,
) {
    NavigationRail {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically)
        ) {
            items.forEach { navigationItem ->
                val selected = navigationItem.selected(navController)

                NavigationRailItem(
                    colors = NavigationRailItemDefaults.colors(
                        selectedIconColor = androidx.compose.material.MaterialTheme.colors.secondaryVariant,
                        selectedTextColor = androidx.compose.material.MaterialTheme.colors.secondaryVariant,
                    ),
                    selected = selected,
                    onClick = {
                        navController.navigate(navigationItem.route) {
                            launchSingleTop = true
                        }
                    },
                    icon = {
                        NavigationIcon(
                            item = navigationItem,
                            selected = selected
                        )
                    },
                    label = {
                        Text(
                            text = navigationItem.title,
                            fontSize = MaterialTheme.typography.bodySmall.fontSize
                        )
                    },
                )
            }
        }
    }
}