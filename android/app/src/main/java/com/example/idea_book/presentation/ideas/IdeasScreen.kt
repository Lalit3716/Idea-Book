package com.example.idea_book.presentation.ideas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import kotlinx.coroutines.flow.collectLatest

@Destination
@Composable
fun IdeasScreen(
    navigator: DestinationsNavigator,
    viewModel: IdeasViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val user = viewModel.user

    val scaffoldState = rememberScaffoldState()

    if (state.isLoading) {
        BlankScreen(showLoadingSpinner = true)
        return
    }

    LaunchedEffect(key1 = true) {
        viewModel.events.collectLatest {
            when (it) {
                is IdeasViewModel.UIEvent.ShowSnackBar -> {
                    val result = scaffoldState.snackbarHostState.showSnackbar(
                        message = "Idea deleted successfully",
                        actionLabel = "Undo"
                    )

                    if (result == SnackbarResult.ActionPerformed) {
                        viewModel.onEvent(IdeasScreenEvent.RestoreIdea)
                    }
                }
            }
        }
    }

    Layout(
        navigator = navigator,
        scaffoldState = scaffoldState,
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
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    item {
                        Text(
                            modifier = Modifier.padding(16.dp),
                            text = "Hi ${user?.displayName}, here are some fresh ideas for you!",
                            style = MaterialTheme.typography.h5
                        )
                    }
                    items(state.ideas) {
                        IdeaItem(
                            idea = it,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            showDelete = user?.uid == it.user_id,
                            onDeleteClick = { viewModel.onEvent(IdeasScreenEvent.DeleteIdea(it)) }
                        )
                    }
                }
            }
        }
    }
}
