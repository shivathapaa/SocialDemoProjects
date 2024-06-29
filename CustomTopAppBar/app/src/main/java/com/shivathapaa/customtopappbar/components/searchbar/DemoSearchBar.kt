package com.shivathapaa.customtopappbar.components.searchbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DemoSearchBar(
    modifier: Modifier = Modifier,
    openDrawer: () -> Unit = {}, /* Will not use drawer for this demo */
    onProfileClick: () -> Unit = {}
) {
    var query by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }

    val keyboardController = LocalSoftwareKeyboardController.current
    val coroutineScope = rememberCoroutineScope()

    SearchBar(
        modifier = modifier,
        query = query,
        onQueryChange = { query = it },
        onSearch = {
            coroutineScope.launch { keyboardController?.hide() }
            // other search action
        },
        active = active,
        onActiveChange = { active = it },
        placeholder = { Text(text = "Search your music") },
        leadingIcon = {
            if (active) {
                IconButton(onClick = {
                    active = false
                    query = ""
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Go Back"
                    )
                }
            } else {
                IconButton(onClick = { openDrawer() }) {
                    Icon(
                        imageVector = Icons.Rounded.Menu,
                        contentDescription = "Open navigation drawer"
                    )
                }
            }
        },
        trailingIcon = {
            var closeDescription = "Close"
            if (active) {
                IconButton(onClick = {
                    if (query.isNotEmpty()) {
                        query = ""
                        closeDescription = "Clear"
                    } else {
                        active = false
                        closeDescription = "Close"
                    }
                }) {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = closeDescription
                    )
                }
            } else {
                IconButton(onClick = { onProfileClick() }) {
                    Icon(imageVector = Icons.Rounded.AccountCircle, contentDescription = "Profile")
                }
            }
        }
    ) {
        SingleSelectionSearchFilterChip()
    }
}
