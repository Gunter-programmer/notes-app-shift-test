package com.example.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.detail.presentation.DetailsRoute
import com.example.notes.list.NotesRoute
import com.example.notesapp.NotesApp

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val app = LocalContext.current.applicationContext as NotesApp

    NavHost(
        navController = navController,
        startDestination = "notes"
    ) {

        composable("notes") {
            NotesRoute(
                observeNotesUseCase = app.observeNotesUseCase,
                onAddNoteClick = {
                    navController.navigate("detail")
                },
                onNoteClick = { noteId ->
                    navController.navigate("detail/$noteId")
                },
            )
        }

        composable("detail") {
            DetailsRoute(
                noteId = null,
                getNoteByIdUseCase = app.getNoteByIdUseCase,
                saveNoteUseCase = app.saveNoteUseCase,
                deleteNoteUseCase = app.deleteNoteUseCase,
                onBackClick = navController::popBackStack,
                onSavedClick = navController::popBackStack,
                onDeletedClick = navController::popBackStack
            )
        }

        composable("detail/{noteId}") { backStackEntry ->
            val noteId = backStackEntry.arguments
                ?.getString("noteId")
                ?.toLongOrNull()

            DetailsRoute(
                noteId = noteId,
                getNoteByIdUseCase = app.getNoteByIdUseCase,
                saveNoteUseCase = app.saveNoteUseCase,
                deleteNoteUseCase = app.deleteNoteUseCase,
                onBackClick = navController::popBackStack,
                onSavedClick = navController::popBackStack,
                onDeletedClick = navController::popBackStack
            )
        }
    }
}