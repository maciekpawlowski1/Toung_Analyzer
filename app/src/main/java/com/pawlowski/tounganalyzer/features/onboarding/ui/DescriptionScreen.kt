package com.pawlowski.tounganalyzer.features.onboarding.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DescriptionScreen(
    onNavigateToNextScreen: () -> Unit,
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(15.dp))

            Text(text = "Opis jak wykonać zdjęcie")

            Spacer(modifier = Modifier.weight(1f))

            Button(onClick = onNavigateToNextScreen) {
                Text(text = "Zrób zdjęcie języka")
            }
        }
    }
}