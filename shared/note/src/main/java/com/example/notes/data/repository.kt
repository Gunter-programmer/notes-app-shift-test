package com.example.notes.data

import com.example.notes.domain.Note
import com.example.notes.domain.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteRepositoryImpl(
    private val noteDao: NoteDao,
) : NoteRepository {

    override fun observeNotes(query: String): Flow<List<Note>> {
        return noteDao.observeNotes(query).map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun getNoteById(id: Long): Note? {
        return noteDao.getNoteById(id)?.toDomain()
    }

    override suspend fun saveNote(note: Note) {
        noteDao.upsertNote(note.toEntity())
    }

    override suspend fun deleteNote(id: Long) {
        noteDao.deleteNoteById(id)
    }
}