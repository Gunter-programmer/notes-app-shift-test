package com.example.detail.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notes.domain.DeleteNoteUseCase
import com.example.notes.domain.GetNoteByIdUseCase
import com.example.notes.domain.SaveNoteUseCase

@Composable
fun DetailsRoute(
    noteId: Long?,
    getNoteByIdUseCase: GetNoteByIdUseCase,
    saveNoteUseCase: SaveNoteUseCase,
    deleteNoteUseCase: DeleteNoteUseCase,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onSavedClick: () -> Unit = onBackClick,
    onDeletedClick: () -> Unit = onBackClick
) {
    val factory = remember(noteId, getNoteByIdUseCase, saveNoteUseCase) {
        DetailsViewModelFactory(
            noteId = noteId,
            getNoteByIdUseCase = getNoteByIdUseCase,
            saveNoteUseCase = saveNoteUseCase,
            deleteNoteUseCase = deleteNoteUseCase,
        )
    }

    val viewModel: DetailsViewModel = viewModel(factory = factory)
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) {
        viewModel.saved.collect {
            onSavedClick()
        }
    }
    LaunchedEffect(viewModel) {
        viewModel.deleted.collect {
            onDeletedClick()
        }
    }

    DetailsScreen(
        state = state,
        onTitleChange = viewModel::onTitleChange,
        onDescriptionChange = viewModel::onDescriptionChange,
        onBackClick = onBackClick,
        onSaveClick = viewModel::onSaveClick,
        onDeleteClick = viewModel::onDeleteClick,
        modifier = modifier,
    )
}