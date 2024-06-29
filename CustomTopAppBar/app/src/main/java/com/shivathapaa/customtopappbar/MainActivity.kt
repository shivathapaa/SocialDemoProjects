package com.shivathapaa.customtopappbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.shivathapaa.customtopappbar.components.CustomFlexibleTopAppBar
import com.shivathapaa.customtopappbar.components.random.RandomTopBarComposable
import com.shivathapaa.customtopappbar.components.searchbar.DemoSearchBar
import com.shivathapaa.customtopappbar.ui.theme.CustomTopAppBarTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CustomTopAppBarTheme {
                MainScreenContent(modifier = Modifier.fillMaxSize())
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainScreenContent(
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomFlexibleTopAppBar(scrollBehavior = scrollBehavior) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                ) { DemoSearchBar() }

                // Comment above Column composable and try below composable for more clarity
//                RandomTopBarComposable(modifier = Modifier.fillMaxWidth()) /* You can use any composable that your app requires */
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        ListOfNumbers(
            modifier = Modifier.fillMaxSize(),
            innerPadding = innerPadding,
            snackbarHostState = snackbarHostState
        )
    }
}

@Composable
private fun ListOfNumbers(
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues,
    snackbarHostState: SnackbarHostState,
) {
    val coroutineScope = rememberCoroutineScope()

    LazyColumn(
        modifier = modifier,
        contentPadding = innerPadding,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val list = (1..100).map { it.toString() }

        itemsIndexed(items = list) { index, item ->
            ListItem(
                modifier = Modifier.clickable(onClick = {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("Clicked on item $item")
                    }
                }),
                headlineContent = { Text(text = "Item indexed at $index") },
                leadingContent = { Text(text = item) }
            )
        }

        item { Text(text = "Keep exploring!\nThere are many things you can do with it.") }
    }
}