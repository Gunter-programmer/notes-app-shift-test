package com.example.detail.presentation

data class DetailsState(
    val noteId: Long? = null,
    val title: String = "",
    val description: String = "",
    val createdAt: Long = 0L,
)