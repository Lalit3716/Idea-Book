package com.example.idea_book.presentation.ideas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.idea_book.core.presentation.components.BlankScreen
import com.example.idea_book.presentation.destinations.AuthScreenDestination
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
            Text(text = "Welcome back ${user.displayName}", style = MaterialTheme.typography.h5, textAlign = TextAlign.Center)
            Button(onClick = viewModel::signOut) {
                Text(text = "Sign out")
            }
        }
    }
}
