package com.pawlowski.tounganalyzer

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.pawlowski.tounganalyzer.features.take_photo.ui.TakePhotoScreen
import com.pawlowski.tounganalyzer.ui.navigation.AppNavHost
import com.pawlowski.tounganalyzer.ui.theme.ToungAnalyzerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToungAnalyzerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavHost(
                        isTakingPhotoPermissionPermanentlyDenied = {
                            shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)
                        }
                    )
                }
            }
        }
    }
}
