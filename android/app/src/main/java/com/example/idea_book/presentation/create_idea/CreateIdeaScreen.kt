package com.example.idea_book.presentation.create_idea

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.idea_book.core.constants.Save
import com.example.idea_book.domain.model.IdeaModel
import com.example.idea_book.presentation.common.layout.Layout
import com.example.idea_book.presentation.create_idea.components.TransparentHintTextField
import com.example.idea_book.presentation.destinations.IdeasScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Destination
@Composable
fun CreateIdeaScreen(
    navigator: DestinationsNavigator,
    viewModel: CreateIdeaViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val title = viewModel.ideaTitle.value
    val content = viewModel.ideaContent.value

    val animatableColor = remember {
        Animatable(
            Color(viewModel.ideaColor.value)
        )
    }

    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModel.events.collectLatest {
            when (it) {
                is CreateIdeaViewModel.UIEvents.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(it.message)
                    navigator.navigate(IdeasScreenDestination)
                }
            }
        }
    }

    Layout(navigator, scaffoldState = scaffoldState, floatingActionButton = {
        FloatingActionButton(onClick = { viewModel.onEvent(CreateIdeaEvent.SaveIdea) }) {
            Icon(Save, contentDescription = "Save")
        }
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(animatableColor.value)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IdeaModel.ideaColors.forEach { color ->
                    val colorInt = color.toArgb()
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .shadow(15.dp, CircleShape)
                            .clip(CircleShape)
                            .background(color)
                            .border(
                                width = 3.dp,
                                color = if (viewModel.ideaColor.value == colorInt) {
                                    Color.Black
                                } else Color.Transparent,
                                shape = CircleShape
                            )
                            .clickable {
                                scope.launch {
                                    animatableColor.animateTo(
                                        targetValue = Color(colorInt),
                                        animationSpec = tween(
                                            durationMillis = 500
                                        )
                                    )
                                }
                                viewModel.onEvent(CreateIdeaEvent.ChangeColor(colorInt))
                            }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                text = title.text,
                hint = title.hint,
                onValueChange = {
                    viewModel.onEvent(CreateIdeaEvent.EnteredTitle(it))
                },
                onFocusChange = {
                    viewModel.onEvent(CreateIdeaEvent.ChangeTitleFocus(it))
                },
                isHintVisible = title.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.h5
            )
            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                text = content.text,
                hint = content.hint,
                onValueChange = {
                    viewModel.onEvent(CreateIdeaEvent.EnteredContent(it))
                },
                onFocusChange = {
                    viewModel.onEvent(CreateIdeaEvent.ChangeContentFocus(it))
                },
                isHintVisible = content.isHintVisible,
                textStyle = MaterialTheme.typography.body1,
                modifier = Modifier.fillMaxHeight()
            )
        }
    }
}
