package com.example.detail.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DetailsRoute(
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel = viewModel(),
    onBackClick: () -> Unit,
    onSavedClick: () -> Unit = onBackClick,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    DetailsScreen(
        state = state,
        onTitleChange = viewModel::onTitleChange,
        onDescriptionChange = viewModel::onDescriptionChange,
        onBackClick = onBackClick,
        onSaveClick = {
            viewModel.onSaveClick()
            onSavedClick()
        },
        modifier = modifier,
    )
}