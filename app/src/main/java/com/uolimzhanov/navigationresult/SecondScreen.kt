package com.uolimzhanov.navigationresult

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uolimzhanov.navigationresult.ui.SecondViewModel

/**
 * Created by uolimzhanov on 12.08.2024
 */
@Composable
fun SecondScreenRoot(
    viewModel: SecondViewModel = hiltViewModel<SecondViewModel>(),
    onClick: () -> Unit
) {
    val text by viewModel.text.collectAsStateWithLifecycle()

    SecondScreen(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .statusBarsPadding(),
        text = text,
        onClick = onClick
    )
}

@Composable
fun SecondScreen(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = text)
        Button(onClick = onClick) {
            Text(text = "Specify the name")
        }
    }
}