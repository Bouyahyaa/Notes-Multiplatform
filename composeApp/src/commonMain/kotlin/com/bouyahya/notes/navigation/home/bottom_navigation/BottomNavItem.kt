package com.bouyahya.notes.navigation.home.bottom_navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bouyahya.notes.navigation.home.utils.NavigationItem

@Composable
fun RowScope.BottomNavItem(
    navigationItem: NavigationItem,
    selected: Boolean,
    onTabChanged: () -> Unit
) {
    BottomNavigationItem(
        modifier = Modifier
            .background(androidx.compose.material3.MaterialTheme.colorScheme.surface)
            .height(58.dp)
            .clip(RoundedCornerShape(16.dp)),
        selected = selected,
        onClick = {
            onTabChanged()
        },
        icon = {
            Icon(
                imageVector = if (selected)
                    navigationItem.selectedIcon else
                    navigationItem.unselectedIcon,
                contentDescription = navigationItem.title,
                tint = if (selected)
                    MaterialTheme.colors.secondaryVariant else
                    Color.Black
            )
        },
        label = {
            Text(
                navigationItem.title,
                fontSize = 12.sp,
                color = if (selected) MaterialTheme.colors.secondaryVariant else
                    Color.Black
            )
        },
        enabled = true,
        alwaysShowLabel = true,
        interactionSource = MutableInteractionSource(),
        selectedContentColor = MaterialTheme.colors.secondaryVariant,
        unselectedContentColor = Color.Black
    )
}