package com.example.detail.navigation

import kotlinx.serialization.Serializable

@Serializable
data class DetailRouter(
    val noteId: Long? = null,
)