package com.uolimzhanov.navigationresult

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

/**
 * Created by uolimzhanov on 12.08.2024
 */
@Composable
fun ScreenWithResultRoot(
    viewModel: ResultReturningViewModel = hiltViewModel(),
    onGoBack: (String) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ScreenWithResult(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .statusBarsPadding(),
        onNameChange = viewModel::onNameChange,
        screenState = state,
        onGoBack = onGoBack
    )
}

@Composable
fun ScreenWithResult(
    modifier: Modifier = Modifier,
    screenState: ResultReturningScreenState = ResultReturningScreenState(),
    onNameChange: (String) -> Unit = {},
    onGoBack: (String) -> Unit = {}
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Write down your name:",
            style = MaterialTheme.typography.displayLarge
        )
        OutlinedTextField(
            value = screenState.username,
            onValueChange = onNameChange
        )
        Button(
            onClick = {
                onGoBack(screenState.username)
            }
        ) {
            Text(text = "Go back with result")
        }
    }
}