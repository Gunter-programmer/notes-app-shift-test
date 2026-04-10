package com.example.notes.domain

import kotlinx.coroutines.flow.Flow

class ObserveNotesUseCase(private val repository: NoteRepository, ) {
    operator fun invoke(query: String): Flow<List<Note>> {
        return repository.observeNotes(query.trim())
    }
}

class GetNoteByIdUseCase(private val repository: NoteRepository, ) {
    suspend operator fun invoke(id: Long): Note? {
        return repository.getNoteById(id)
    }
}

class SaveNoteUseCase(private val repository: NoteRepository, ) {
    suspend operator fun invoke(note: Note) {
        repository.saveNote(note)
    }
}

class DeleteNoteUseCase(private val repository: NoteRepository, ) {
    suspend operator fun invoke(id: Long) {
        repository.deleteNote(id)
    }
}