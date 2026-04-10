package com.example.notes.list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.notes.presentation.NotesState

class NotesViewModel : ViewModel() {

    private val _state = MutableStateFlow(NotesState())
    val state: StateFlow<NotesState> = _state.asStateFlow()

    fun onSearchQueryChange(query: String) {
        _state.value = _state.value.copy(
            searchQuery = query
        )
    }
}