package com.example.idea_book.presentation.profile

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.idea_book.ui.theme.IdeaBookTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun ProfileScreen(
    navigator: DestinationsNavigator? = null
) {
    Text(text = "Profile Screen")
}

@Preview
@Composable
fun ProfileScreenPreview() {
    IdeaBookTheme {
        ProfileScreen()
    }
}
