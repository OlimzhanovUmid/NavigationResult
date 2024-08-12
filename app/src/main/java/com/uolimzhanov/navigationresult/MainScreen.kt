package com.uolimzhanov.navigationresult

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

/**
 * Created by uolimzhanov on 12.08.2024
 */
@Composable
fun MainScreenRoot(
    viewModel: MainViewModel = hiltViewModel(),
    onNextClick: () -> Unit,
    onGoToSecondScreen: () -> Unit
) {
    val greetingText by viewModel.greetingMessage.collectAsStateWithLifecycle()

    MainScreen(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .statusBarsPadding(),
        greetingText = greetingText,
        onNextClick = onNextClick,
        onGoToSecondScreen = onGoToSecondScreen
    )
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    greetingText: String,
    onNextClick: () -> Unit = {},
    onGoToSecondScreen: () -> Unit
) {
    Box(modifier = modifier) {
        Text(
            text = greetingText,
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.align(Alignment.Center)
        )

        IconButton(
            modifier = Modifier
                .padding(24.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                )
                .align(Alignment.BottomEnd),
            onClick = onNextClick
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowForward,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }

        Button(
            modifier = Modifier
                .padding(24.dp)
                .align(Alignment.BottomStart),
            onClick = onGoToSecondScreen
        ) {
            Text(text = "Go to second screen")
        }
    }
}
