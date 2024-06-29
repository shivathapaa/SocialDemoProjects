package com.shivathapaa.customtopappbar.components.searchbar

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp


@Composable
fun SingleSelectionSearchFilterChip(
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    var selectedCategory by remember { mutableIntStateOf(0) }

    Row(
        modifier = modifier.horizontalScroll(scrollState)
    ) {
        listOfDemoSearchCategories.forEachIndexed { index, category ->
            FilterChip(
                modifier = Modifier.padding(horizontal = 6.dp),
                selected = selectedCategory == index,
                onClick = { selectedCategory = index },
                label = { Text(text = category.label) },
                leadingIcon = { Icon(imageVector = category.filledIcon, contentDescription = null) }
            )
        }
    }

}

data class DemoSearchCategory(
    val label: String,
    val route: String,
    val onClick: () -> Unit = {},
    val filledIcon: ImageVector,
    val outlinedIcon: ImageVector
)


val listOfDemoSearchCategories = listOf(
    DemoSearchCategory(
        label = "Home",
        route = "home",
        onClick = {},
        filledIcon = Icons.Filled.Home,
        outlinedIcon = Icons.Outlined.Home
    ),
    DemoSearchCategory(
        label = "Mail",
        route = "mail",
        onClick = {},
        filledIcon = Icons.Filled.Email,
        outlinedIcon = Icons.Outlined.Email
    ),
    DemoSearchCategory(
        label = "Notifications",
        route = "notifications",
        onClick = {},
        filledIcon = Icons.Filled.Notifications,
        outlinedIcon = Icons.Outlined.Notifications
    ),
    DemoSearchCategory(
        label = "Profile",
        route = "profile",
        onClick = {},
        filledIcon = Icons.Filled.Person,
        outlinedIcon = Icons.Outlined.Person
    )
)