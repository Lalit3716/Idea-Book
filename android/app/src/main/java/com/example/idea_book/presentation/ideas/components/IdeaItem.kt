package com.example.idea_book.presentation.ideas.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.idea_book.domain.model.IdeaModel
import com.example.idea_book.domain.model.TagModel
import com.example.idea_book.presentation.common.TagList

@Composable
fun IdeaItem(
    idea: IdeaModel,
    selectedTags: List<TagModel>,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier,
        elevation = 8.dp,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(end = 2.dp)
        ) {
            TagList(tags = idea.tags, selectedTags = selectedTags)
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = idea.title,
                style = MaterialTheme.typography.h6,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(1.dp))
            Text(
                text = idea.username,
                style = MaterialTheme.typography.body2.copy(
                    fontStyle = FontStyle.Italic,
                    fontWeight = MaterialTheme.typography.h6.fontWeight
                ),

                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = idea.description,
                style = MaterialTheme.typography.body1,
                maxLines = 10,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}