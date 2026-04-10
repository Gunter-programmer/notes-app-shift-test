package com.example.detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notes.domain.GetNoteByIdUseCase
import com.example.notes.domain.SaveNoteUseCase

class DetailsViewModelFactory(
    private val noteId: Long?,
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val saveNoteUseCase: SaveNoteUseCase,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(
                noteId = noteId,
                getNoteByIdUseCase = getNoteByIdUseCase,
                saveNoteUseCase = saveNoteUseCase,
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}