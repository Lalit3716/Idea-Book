package com.example.idea_book.presentation.create_idea

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.idea_book.presentation.common.layout.Layout
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun CreateIdeaScreen(
    navigator: DestinationsNavigator
) {
    Layout(navigator) {
        Text(text = "Create Idea Screen")
    }
}
