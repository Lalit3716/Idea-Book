package com.example.idea_book.presentation.ideas

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.idea_book.presentation.destinations.AuthScreenDestination
import com.example.idea_book.ui.theme.IdeaBookTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun IdeasScreen(
    navigator: DestinationsNavigator? = null,
    viewModel: IdeasViewModel = hiltViewModel()
) {
    val isAuth = viewModel.isAuth
    LaunchedEffect(key1 = isAuth) {
        if (!isAuth) {
            navigator?.navigate(AuthScreenDestination())
        }
    }

    Column {
        Text(text = "Welcome Back ${viewModel.getUser()?.displayName}")
        Button(onClick = viewModel::logout) {
            Text(text = "Logout")
        }
    }
}

@Preview
@Composable
fun IdeasScreenPreview() {
    IdeaBookTheme {
        IdeasScreen()
    }
}
