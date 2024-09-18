package com.mynotes.myapplication.feature.notes.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkAdded
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.outlined.DeleteSweep
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mynotes.myapplication.feature.core.presentation.MainViewModel
import com.mynotes.myapplication.feature.core.ui.theme.poppinsFontFamily
import com.mynotes.myapplication.feature.notes.domain.model.Note
import kotlin.random.Random

@Composable
fun NoteCard(
    note: Note,
    onEditNoteClick:(Int, String,String) -> Unit,
    onUndoDeleteClick:() -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    val backgroundColor = getRandomColor()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 2.dp, vertical = 4.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor)
                .padding(12.dp)
        ) {
            if(!note.isBookmarked) {
                Spacer(modifier = Modifier.size(8.dp))
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = note.title,
                    modifier = Modifier
                        .weight(8.5f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = poppinsFontFamily,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
                if(note.isBookmarked) {
                    IconButton(
                        modifier = Modifier
                            .weight(1.5f),
                        onClick = { }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.BookmarkAdded ,
                            contentDescription = "Bookmark",
                            modifier = Modifier
                                .size(24.dp)
                        )
                    }
                }
            }
            if(!note.description.isNullOrBlank()) {
                Text(
                    text = note.description,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp,
                    maxLines = 7,
                    overflow = TextOverflow.Ellipsis
                )
            }

            HorizontalDivider(
                modifier = Modifier
                    .padding(top = if(note.description.isNullOrBlank()) 4.dp else 8.dp),
                color = MaterialTheme.colorScheme.secondary,
                thickness = 0.5.dp
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = {
                        viewModel.deleteNote(note)
                        onUndoDeleteClick()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.DeleteSweep,
                        contentDescription = "Delete",
                        modifier = Modifier
                            .size(20.dp)
                    )
                }
                IconButton(
                    onClick = {
                        note.description?.let { onEditNoteClick(note.id, note.title, it) }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.EditNote,
                        contentDescription = "Edit Note",
                        modifier = Modifier
                            .size(20.dp)
                    )
                }
            }
        }
    }
}

val beautifulColors = listOf(
    Color(0xFFFFCDD2), // Red 100
    Color(0xFFF8BBD0), // Pink 100
    Color(0xFFE1BEE7), // Purple 100
    Color(0xFFD1C4E9), // Deep Purple 100
    Color(0xFFC5CAE9), // Indigo 100
    Color(0xFFBBDEFB), // Blue 100
    Color(0xFFB3E5FC), // Light Blue 100
    Color(0xFFB2EBF2), // Cyan 100
    Color(0xFFB2DFDB), // Teal 100
    Color(0xFFC8E6C9), // Green 100
    Color(0xFFDCEDC8), // Light Green 100
    Color(0xFFF0F4C3), // Lime 100
    Color(0xFFFFF9C4), // Yellow 100
    Color(0xFFFFECB3), // Amber 100
    Color(0xFFFFE0B2), // Orange 100
    Color(0xFFFFCCBC), // Deep Orange 100
    Color(0xFFD7CCC8), // Brown 100
    Color(0xFFCFD8DC)  // Blue Grey 100
)

fun getRandomColor(): Color {
    return beautifulColors[Random.nextInt(beautifulColors.size)]
}

data class Note(
    val id: Int,
    val title: String,
    val description: String?,
    val isBookmarked: Boolean,
    val backgroundColor: Color = getRandomColor() // Default to a random color
)
