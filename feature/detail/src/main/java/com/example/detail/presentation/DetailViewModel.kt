package com.example.detail.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailsViewModel : ViewModel() {
    private val _state = MutableStateFlow(DetailsState())
    val state: StateFlow<DetailsState> = _state.asStateFlow()

    fun onTitleChange(title: String) {
        _state.value = _state.value.copy(
            title = title
        )
    }

    fun onDescriptionChange(description: String) {
        _state.value = _state.value.copy(
            description = description
        )
    }

    fun onSaveClick() {
        //Логика сохранения заметки
    }
}