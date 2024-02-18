package com.bouyahya.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.bouyahya.notes.features.notes.ui.allnotes.NotesScreenTab
import com.bouyahya.notes.features.profile.ui.ProfileScreenTab
import com.bouyahya.notes.core.navigation.rails.NavigationItem
import com.bouyahya.notes.core.navigation.rails.NavigationSideBar

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun App() {
    MaterialTheme {
        val items = listOf(
            NavigationItem(
                title = "Home",
                selectedIcon = Icons.Default.Home,
                unselectedIcon = Icons.Outlined.Home,
                tab = NotesScreenTab
            ),
            NavigationItem(
                title = "Profile",
                selectedIcon = Icons.Filled.Person,
                unselectedIcon = Icons.Outlined.Person,
                tab = ProfileScreenTab
            )
        )

        val windowClass = calculateWindowSizeClass()
        val showNavigationRail = windowClass.widthSizeClass != WindowWidthSizeClass.Compact
        var selectedItemIndex by rememberSaveable {
            mutableStateOf(0)
        }

        TabNavigator(NotesScreenTab) { tabNavigator ->
            Scaffold(bottomBar = {
                if (!showNavigationRail) {
                    BottomNavigation(
                        modifier = Modifier.fillMaxWidth().windowInsetsPadding(WindowInsets.ime),
                        backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.background,
                        contentColor = contentColorFor(MaterialTheme.colors.secondaryVariant),
                        elevation = 8.dp
                    ) {
                        items.map { navigationItem ->
                            val index = navigationItem.tab.options.index.toInt()
                            TabItem(
                                tab = navigationItem.tab,
                                tabNavigator = tabNavigator,
                                onTabChanged = {
                                    selectedItemIndex = index
                                }
                            )
                        }
                    }
                }
            }) {
                Column(
                    modifier = Modifier.fillMaxSize().padding(
                        bottom = it.calculateBottomPadding(),
                        start = if (showNavigationRail) 80.dp else 0.dp
                    )
                ) {
                    CurrentTab()
                }
            }

            if (showNavigationRail) {
                NavigationSideBar(
                    items = items,
                    selectedItemIndex = selectedItemIndex,
                    onNavigate = {
                        selectedItemIndex = it
                        when (selectedItemIndex) {
                            0 -> {
                                tabNavigator.current = NotesScreenTab
                            }

                            1 -> {
                                tabNavigator.current = ProfileScreenTab
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun RowScope.TabItem(tab: Tab, tabNavigator: TabNavigator, onTabChanged: () -> Unit) {
    BottomNavigationItem(
        modifier = Modifier
            .background(androidx.compose.material3.MaterialTheme.colorScheme.surface)
            .height(58.dp)
            .clip(RoundedCornerShape(16.dp)),
        selected = tabNavigator.current == tab,
        onClick = {
            tabNavigator.current = tab
            onTabChanged()
        },
        icon = {
            tab.options.icon?.let { painter ->
                Icon(
                    painter,
                    contentDescription = tab.options.title,
                    tint = if (tabNavigator.current == tab)
                        MaterialTheme.colors.secondaryVariant else
                        Color.Black
                )
            }
        },
        label = {
            tab.options.title.let { title ->
                Text(
                    title,
                    fontSize = 12.sp,
                    color = if (tabNavigator.current == tab) MaterialTheme.colors.secondaryVariant else Color.Black
                )
            }
        },
        enabled = true,
        alwaysShowLabel = true,
        interactionSource = MutableInteractionSource(),
        selectedContentColor = MaterialTheme.colors.secondaryVariant,
        unselectedContentColor = Color.Black
    )
}