package com.example.notes.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.notes.presentation.NotesState
import com.example.notes.presentation.components.NotesGrid
import com.example.notes.presentation.components.NotesSearch

@Composable
fun NotesScreen(
    state: NotesState,
    onSearchQueryChange: (String) -> Unit,
    onAddNoteClick: () -> Unit,
    onNoteClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddNoteClick,
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Добавить заметку",
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = modifier.fillMaxSize()
                .padding(innerPadding)
        ) {
            NotesSearch(
                text = state.searchQuery,
                onValueChange = onSearchQueryChange,
            )

            if (state.notes.isEmpty()) {
                EmptyNotesContent(
                    modifier = Modifier.weight(1f)
                )
            } else {
                NotesGrid(
                    notes = state.notes,
                    onNoteClick = onNoteClick,
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}

@Composable
private fun EmptyNotesContent(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "Создайте свою первую заметку!",
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}