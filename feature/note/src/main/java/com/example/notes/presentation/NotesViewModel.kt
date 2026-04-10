package com.example.notes.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.domain.ObserveNotesUseCase
import com.example.notes.presentation.NoteUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.notes.presentation.NotesState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class NotesViewModel(private val observeNotesUseCase: ObserveNotesUseCase)
    : ViewModel() {

    private val _state = MutableStateFlow(NotesState())

    fun onSearchQueryChange(query: String) {
        _state.value = _state.value.copy(
            searchQuery = query
        )
    }

    val state: StateFlow<NotesState> = observeNotesUseCase("")
        .map { notes ->
            NotesState(
                notes = notes.map { note ->
                    NoteUi(
                        id = note.id,
                        title = note.title,
                        content = note.content,
                    )
                }
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = NotesState(),
        )

}