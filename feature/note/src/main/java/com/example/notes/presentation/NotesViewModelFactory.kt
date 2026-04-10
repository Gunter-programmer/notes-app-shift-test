package com.example.notes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notes.domain.ObserveNotesUseCase
import com.example.notes.list.NotesViewModel

class NotesViewModelFactory(
    private val observeNotesUseCase: ObserveNotesUseCase,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotesViewModel::class.java)) {
            return NotesViewModel(
                observeNotesUseCase = observeNotesUseCase,
            ) as T
        }
        throw IllegalArgumentException("Неизвестный класс: ${modelClass.name}")
    }
}