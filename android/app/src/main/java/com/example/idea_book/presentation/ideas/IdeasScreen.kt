package com.example.idea_book.presentation.ideas

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.idea_book.presentation.common.layout.Layout
import com.example.idea_book.presentation.destinations.CreateIdeaScreenDestination
import com.example.idea_book.presentation.ideas.components.NoContent
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun IdeasScreen(
    navigator: DestinationsNavigator,
    viewModel: IdeasViewModel = hiltViewModel()
) {
    Layout(
        navigator = navigator,
        floatingActionButton = {
            FloatingActionButton(onClick = { navigator.navigate(CreateIdeaScreenDestination) }) {
                Icon(Icons.Filled.Add, "addIcon")
            }
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.onBackground
        ) {
            NoContent()
        }
    }
}
