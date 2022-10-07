package com.example.idea_book.presentation.ideas

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.idea_book.presentation.destinations.AuthScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun IdeasScreen(
    navigator: DestinationsNavigator? = null,
    viewModel: IdeasViewModel = hiltViewModel()
) {
    val isAuth = viewModel.isAuth
    val user = viewModel.user
    val token = viewModel.token

    LaunchedEffect(isAuth) {
        if (!isAuth) {
            navigator?.navigate(AuthScreenDestination)
        }
    }

    if (user == null) {
        return
    }

    Column {
        Text(text = "Welcome back ${user.displayName}")
        Text(text = "Your id token is $token")
        Button(onClick = viewModel::signOut) {
            Text(text = "Sign out")
        }
    }
}
