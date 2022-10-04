package com.example.idea_book.presentation.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.idea_book.R
import com.example.idea_book.presentation.destinations.IdeaScreenDestination
import com.example.idea_book.ui.theme.IdeaBookTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)
@Composable
fun AuthScreen(
    navigator: DestinationsNavigator? = null
) {
    val scrollState = rememberScrollState()

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        contentColor = MaterialTheme.colors.onBackground,
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.idea),
                contentDescription = "Logo",
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AuthScreenPreview() {
    IdeaBookTheme {
        AuthScreen()
    }
}
