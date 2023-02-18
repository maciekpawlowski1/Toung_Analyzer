package com.pawlowski.tounganalyzer.features.onboarding.ui

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pawlowski.tounganalyzer.R

@Composable
fun StartingScreen(
    onNavigateToNextScreen: () -> Unit,
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Spacer(modifier = Modifier.height(15.dp))

            Image(
                painter = painterResource(id = R.drawable.starting_screen_image),
                contentDescription = null
            )

            Text(
                text = "Maszynowa diagnoza języka",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "'Pokaż mi swój język, a powiem ci na co chorujesz'",
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(onClick = onNavigateToNextScreen) {
                Text(text = "Zaczynajmy!")
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun StartingScreenPreview() {
    StartingScreen {

    }
}