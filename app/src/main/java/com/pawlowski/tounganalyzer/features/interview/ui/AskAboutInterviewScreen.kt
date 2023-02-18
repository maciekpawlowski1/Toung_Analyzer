package com.pawlowski.tounganalyzer.features.interview.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AskAboutInterviewScreen(
    onNavigateToQuestionsScreen: () -> Unit,
    onNavigateToNextScreen: () -> Unit,
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Dziękujemy!")
            Spacer(modifier = Modifier.height(15.dp))

            Text(text = "Czy chesz odpowiedzieć jeszcze na kilka krótkich pytań o twoim stanie zdrowia?")
            Spacer(modifier = Modifier.height(30.dp))

            Row {
                Button(onClick = onNavigateToQuestionsScreen) {
                    Text(text = "Tak")
                }
                Spacer(modifier = Modifier.width(20.dp))
                Button(onClick = onNavigateToNextScreen, colors = ButtonDefaults.buttonColors(containerColor = Color.Red)) {
                    Text(text = "Nie")
                }
            }
        }
    }
}