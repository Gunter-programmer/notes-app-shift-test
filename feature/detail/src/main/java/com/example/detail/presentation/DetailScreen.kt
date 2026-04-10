package com.example.detail.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.detail.R
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment

@Composable
fun DetailsScreen(
    state: DetailsState,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp)
        ){
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Назад",
                modifier = Modifier
                    .padding(top = 12.dp)
                    .size(24.dp)
                    .clickable(onClick = onBackClick)
            )
            Spacer(modifier = Modifier.weight(1f))
            TextButton(onClick = onSaveClick) {
                Text(text = "Сохранить заметку")
            }
        }
        BasicTextField(
            value = state.title,
            onValueChange = onTitleChange,
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.headlineMedium,
            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
            decorationBox = { innerTextField ->
                if (state.title.isEmpty()) {
                    Text(
                        text = "Заголовок",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                innerTextField()
            }
        )

        Spacer(modifier = Modifier.height(30.dp))

        BasicTextField(
            value = state.description,
            onValueChange = onDescriptionChange,
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.bodyLarge,
            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
            decorationBox = { innerTextField ->
                if (state.description.isEmpty()) {
                    Text(
                        text = "Описание заметки",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                innerTextField()
            }
        )
        if(state.noteId != null) {
            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = onDeleteClick,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Удалить заметку")
            }
        }
    }
}