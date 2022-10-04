package com.example.idea_book.presentation.idea

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.idea_book.ui.theme.IdeaBookTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun IdeaScreen(
    navigator: DestinationsNavigator? = null
) {
    Text("Idea Screen")
}

@Preview
@Composable
fun IdeaScreenPreview() {
    IdeaBookTheme() {
        IdeaScreen()
    }
}
