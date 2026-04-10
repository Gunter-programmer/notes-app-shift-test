package com.example.notesapp

import android.app.Application
import androidx.room.Room
import com.example.notes.domain.DeleteNoteUseCase
import com.example.notes.domain.GetNoteByIdUseCase
import com.example.notes.data.NoteRepositoryImpl
import com.example.notes.data.NotesDatabase
import com.example.notes.domain.ObserveNotesUseCase
import com.example.notes.domain.SaveNoteUseCase

class NotesApp : Application() {

    lateinit var database: NotesDatabase
        private set

    lateinit var repository: com.example.notes.domain.NoteRepository
        private set

    lateinit var observeNotesUseCase: ObserveNotesUseCase
        private set

    lateinit var getNoteByIdUseCase: GetNoteByIdUseCase
        private set

    lateinit var saveNoteUseCase: SaveNoteUseCase
        private set

    lateinit var deleteNoteUseCase: DeleteNoteUseCase
        private set

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            NotesDatabase::class.java,
            "notes.db",
        ).build()

        repository = NoteRepositoryImpl(database.noteDao())

        observeNotesUseCase = ObserveNotesUseCase(repository)
        getNoteByIdUseCase = GetNoteByIdUseCase(repository)
        saveNoteUseCase = SaveNoteUseCase(repository)
        deleteNoteUseCase = DeleteNoteUseCase(repository)
    }
}