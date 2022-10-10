package com.example.idea_book.presentation.ideas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.idea_book.presentation.common.BlankScreen
import com.example.idea_book.presentation.common.layout.Layout
import com.example.idea_book.presentation.destinations.CreateIdeaScreenDestination
import com.example.idea_book.presentation.ideas.components.IdeaItem
import com.example.idea_book.presentation.ideas.components.NoContent
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun IdeasScreen(
    navigator: DestinationsNavigator,
    viewModel: IdeasViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val user = viewModel.user

    if (state.isLoading) {
        BlankScreen(showLoadingSpinner = true)
        return
    }

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
            if (state.ideas.isEmpty()) {
                NoContent()
            } else {
                Spacer(modifier = Modifier.height(8.dp))
                LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                    items(state.ideas) {
                        IdeaItem(
                            idea = it,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            showDelete = viewModel.user?.uid == it.user_id,
                            onDeleteClick = { }
                        )
                    }
                }
            }
        }
    }
}
