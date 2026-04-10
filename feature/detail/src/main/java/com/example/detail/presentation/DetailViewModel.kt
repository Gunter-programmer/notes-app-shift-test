package com.example.detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.domain.DeleteNoteUseCase
import com.example.notes.domain.GetNoteByIdUseCase
import com.example.notes.domain.Note
import com.example.notes.domain.SaveNoteUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(private val noteId: Long?,
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val saveNoteUseCase: SaveNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(DetailsState(noteId = noteId))
    val state: StateFlow<DetailsState> = _state.asStateFlow()

    private val _saved = MutableSharedFlow<Unit>()
    val saved: SharedFlow<Unit> = _saved.asSharedFlow()

    private val _deleted = MutableSharedFlow<Unit>()
    val deleted: SharedFlow<Unit> = _deleted.asSharedFlow()

    init {
        if (noteId != null) {
            loadNote(noteId)
        }
    }

    fun onTitleChange(title: String) {
        _state.value = _state.value.copy(
            title = title
        )
    }

    fun onDescriptionChange(description: String) {
        _state.value = _state.value.copy(
            description = description
        )
    }

    fun onSaveClick() {
        viewModelScope.launch {
            val current = _state.value

            val note = Note(
                id = current.noteId ?: 0L,
                title = current.title,
                content = current.description,
                createdAt = if (current.noteId == null) {
                    System.currentTimeMillis()
                } else {
                    current.createdAt
                },
            )

            saveNoteUseCase(note)
            _saved.emit(Unit)
        }
    }

    fun onDeleteClick(){
        val id = _state.value.noteId ?: return

        viewModelScope.launch {
            deleteNoteUseCase(id)
            _deleted.emit(Unit)
        }
    }

    private fun loadNote(id: Long) {
        viewModelScope.launch {
            val note = getNoteByIdUseCase(id) ?: return@launch

            _state.value = _state.value.copy(
                noteId = note.id,
                title = note.title,
                description = note.content,
                createdAt = note.createdAt,
            )
        }
    }
}