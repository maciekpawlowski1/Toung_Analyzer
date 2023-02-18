package com.pawlowski.tounganalyzer.features.interview.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun QuestionsScreen(
    onNavigateToOnboardingScreen: () -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "Pytanie")

            Button(onClick = onNavigateToOnboardingScreen) {
                Text(text = "Zakończ")
            }
        }
    }
}