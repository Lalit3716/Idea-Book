package com.example.idea_book.presentation.common.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.launch
import com.example.idea_book.domain.model.MenuItem

@Composable
fun Layout(
    floatingActionButton: @Composable () -> Unit = {},
    content: @Composable () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    val drawerItems: List<MenuItem> = listOf(
        MenuItem(
            title = "Home",
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
        ),
        MenuItem(
            title = "Your Ideas",
            icon = {
                Icon(
                    painter = painterResource(id = com.example.idea_book.R.drawable.ic_light_bulb),
                    contentDescription = "Your Ideas"
                )
            },
        ),
        MenuItem(
            title = "Profile",
            icon = { Icon(Icons.Filled.AccountCircle, contentDescription = "Profile") },
        )
    )

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar("Idea Book") {
                coroutineScope.launch {
                    scaffoldState.drawerState.open()
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = floatingActionButton,
        drawerContent = {
            Column {
                DrawerHeader()
                DrawerBody(items = drawerItems, onItemClick = {})
            }
        },
        content = { content() }
    )
}
