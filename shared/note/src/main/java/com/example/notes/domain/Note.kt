package com.example.notes.domain

data class Note(
    val id: Long = 0L,
    val title: String,
    val content: String,
    val createdAt: Long,
)