package com.example.notes.presentation

data class NotesState(
    val searchQuery: String = "",
    val notes: List<NoteUi> = emptyList()
)

data class NoteUi(
    val id: Long,
    val title: String,
    val content: String,
)