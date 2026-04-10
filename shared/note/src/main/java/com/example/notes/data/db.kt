package com.example.notes.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notes.data.NoteDao
import com.example.notes.data.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 1,
    exportSchema = false,
)

abstract class NotesDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}