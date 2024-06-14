package com.bouyahya.notes.navigation.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bouyahya.notes.getPlatform
import com.bouyahya.notes.navigation.Graph
import com.bouyahya.notes.navigation.home.bottom_navigation.BottomNavigationBar
import com.bouyahya.notes.navigation.home.utils.NavigationItem
import com.bouyahya.notes.navigation.home.sidebar_navigation.NavigationSideBar

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun HomeScreen(
    navController:
    NavHostController = rememberNavController()
) {
    val items = arrayListOf(
        NavigationItem(
            title = "Home",
            selectedIcon = Icons.Default.Home,
            unselectedIcon = Icons.Outlined.Home,
            route = Graph.NOTE,
        ),
        NavigationItem(
            title = "Profile",
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.Person,
            route = HomeScreenRoute.Profile.route,
        )
    )

    LaunchedEffect(Unit) {
        if (!getPlatform().name.contains("Java"))
            items.add(
                1,
                NavigationItem(
                    title = "Videos",
                    selectedIcon = Icons.Filled.Share,
                    unselectedIcon = Icons.Outlined.Share,
                    route = HomeScreenRoute.Videos.route,
                )
            )
    }

    val windowClass = calculateWindowSizeClass()
    val showNavigationRail = windowClass.widthSizeClass != WindowWidthSizeClass.Compact

    Scaffold(bottomBar = {
        if (!showNavigationRail) {
            BottomNavigationBar(
                items = items,
                navController = navController,
            )
        }
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    bottom = it.calculateBottomPadding(),
                    start = if (showNavigationRail) 80.dp else 0.dp
                )
        ) {
            HomeNavigationGraph(navController = navController)
        }
    }

    if (showNavigationRail) {
        NavigationSideBar(
            items = items,
            navController = navController,
        )
    }
}