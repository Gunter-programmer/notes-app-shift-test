package com.example.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.detail.navigation.DetailRouter
import com.example.detail.presentation.DetailsRoute as DetailsScreenRoute
import com.example.notes.list.NotesRoute
import com.example.notes.navigation.NoteRouter

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = NoteRouter,
        modifier = modifier,
    ) {
        composable<NoteRouter> {
            NotesRoute(
                onAddNoteClick = {
                    navController.navigate(DetailRouter())
                },
            )
        }

        composable<DetailRouter> { backStackEntry ->
            val destination = backStackEntry.toRoute<DetailRouter>()

            DetailsScreenRoute(
                noteId = destination.noteId,
                onBackClick = {
                    navController.popBackStack()
                },
                onSavedClick = {
                    navController.popBackStack()
                },
            )
        }
    }
}