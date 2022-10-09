package com.example.idea_book.presentation.ideas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.idea_book.presentation.common.BlankScreen
import com.example.idea_book.presentation.common.layout.Layout
import com.example.idea_book.presentation.destinations.AuthScreenDestination
import com.example.idea_book.presentation.destinations.CreateIdeaScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun IdeasScreen(
    navigator: DestinationsNavigator? = null,
    viewModel: IdeasViewModel = hiltViewModel()
) {
    val user = viewModel.user

    LaunchedEffect(user) {
        if (user == null) {
            navigator?.navigate(AuthScreenDestination)
        }
    }

    if (user == null) {
        BlankScreen()
        return
    }

    Layout(
        floatingActionButton = {
            FloatingActionButton(onClick = { navigator?.navigate(CreateIdeaScreenDestination) }) {
                Icon(Icons.Filled.Add, "addIcon")
            }
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.onBackground
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Welcome back ${user.displayName}",
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Center
                )
                Button(onClick = viewModel::signOut) {
                    Text(text = "Sign out")
                }
            }
        }
    }
}
