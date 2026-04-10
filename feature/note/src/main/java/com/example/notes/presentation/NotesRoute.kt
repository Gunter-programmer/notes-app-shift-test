package com.example.notes.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notes.domain.ObserveNotesUseCase
import com.example.notes.presentation.NotesViewModelFactory

@Composable
fun NotesRoute(
    modifier: Modifier = Modifier,
    observeNotesUseCase: ObserveNotesUseCase,
    onAddNoteClick: () -> Unit = {},
    onNoteClick: (Long) -> Unit = {},
) {
    val factory = remember(observeNotesUseCase) {
        NotesViewModelFactory(
            observeNotesUseCase = observeNotesUseCase,
        )
    }
    val viewModel: NotesViewModel = viewModel(
        factory = factory,
    )
    val state by viewModel.state.collectAsStateWithLifecycle()

    NotesScreen(
        state = state,
        onSearchQueryChange = viewModel::onSearchQueryChange,
        onAddNoteClick = onAddNoteClick,
        onNoteClick = onNoteClick,
        modifier = modifier,
    )
}