package com.example.notes.domain

import com.example.notes.domain.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun observeNotes(query: String): Flow<List<Note>>
    suspend fun getNoteById(id: Long): Note?
    suspend fun saveNote(note: Note)
    suspend fun deleteNote(id: Long)
}