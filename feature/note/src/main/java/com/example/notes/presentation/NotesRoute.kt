package com.example.notes.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun NotesRoute(
    modifier: Modifier = Modifier,
    viewModel: NotesViewModel = viewModel(),
    onAddNoteClick: () -> Unit = {},
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    NotesScreen(
        state = state,
        onSearchQueryChange = viewModel::onSearchQueryChange,
        onAddNoteClick = onAddNoteClick,
        modifier = modifier,
    )
}