package com.example.idea_book.presentation.auth

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.idea_book.R
import com.example.idea_book.presentation.auth.components.AuthForm
import com.example.idea_book.presentation.destinations.IdeasScreenDestination
import com.example.idea_book.ui.theme.IdeaImageBg
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)
@Composable
fun AuthScreen(
    navigator: DestinationsNavigator? = null,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val state = viewModel.authState

    LaunchedEffect(key1 = state.isLoading) {
        Log.i("AuthScreen", "LaunchedEffect: $state")
        if (state.isAuth) {
            navigator?.navigate(IdeasScreenDestination())
        }
    }

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        contentColor = MaterialTheme.colors.onBackground,
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = IdeaImageBg
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.idea),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            AuthForm(state = state) {
                viewModel.onEvent(it)
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
