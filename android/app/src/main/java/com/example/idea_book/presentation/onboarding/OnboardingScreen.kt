package com.example.idea_book.presentation.onboarding

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.idea_book.ui.theme.IdeaBookTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun OnBoardingScreen(
    navigator: DestinationsNavigator? = null
) {
    Text(text = "On Boarding Screen")
}

@Preview
@Composable
fun OnBoardingScreenPreview() {
    IdeaBookTheme {
        OnBoardingScreen()
    }
}
