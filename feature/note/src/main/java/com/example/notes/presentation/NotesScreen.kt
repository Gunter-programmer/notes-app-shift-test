package com.example.notes.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notes.presentation.NoteUi
import com.example.notes.presentation.NotesState
import com.example.notes.presentation.components.NotesGrid
import com.example.notes.presentation.components.NotesSearch

@Composable
fun NotesScreen(
    state: NotesState,
    onSearchQueryChange: (String) -> Unit,
    onAddNoteClick: () -> Unit,
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

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Notes screen with content",
)
@Composable
private fun NotesScreenContentPreview() {
    NotesScreen(
        state = NotesState(
            searchQuery = "Работа",
            notes = listOf(
                NoteUi(
                    id = 1L,
                    title = "Рабочие задачи",
                    content = "Закончить экран заметок и подготовить архитектуру модулей.",
                ),
                NoteUi(
                    id = 2L,
                    title = "Покупки",
                    content = "Купить молоко, хлеб, сыр и фрукты.",
                ),
                NoteUi(
                    id = 3L,
                    title = "Идея",
                    content = "Сделать сохранение черновика при выходе с экрана редактирования.",
                ),
                NoteUi(
                    id = 4L,
                    title = "Напоминание",
                    content = "Проверить навигацию между списком заметок и деталями.",
                ),
            ),
        ),
        onSearchQueryChange = {},
        onAddNoteClick = {},
    )
}