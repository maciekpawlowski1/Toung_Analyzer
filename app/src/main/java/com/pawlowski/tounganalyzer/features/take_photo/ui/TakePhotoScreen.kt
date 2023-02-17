package com.pawlowski.tounganalyzer.features.take_photo.ui

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.pawlowski.tounganalyzer.R
import com.pawlowski.tounganalyzer.features.take_photo.view_model_related.ITakePhotoViewModel
import com.pawlowski.tounganalyzer.features.take_photo.view_model_related.TakePhotoViewModel
import com.pawlowski.tounganalyzer.ui.utils.openAppSettings
import java.io.File
import java.util.concurrent.Executors

@Composable
fun TakePhotoScreen(
    viewModel: ITakePhotoViewModel = hiltViewModel<TakePhotoViewModel>()
) {
    val uiState = viewModel.container.stateFlow.collectAsState()

    val context = LocalContext.current

    val showCameraState = remember {
        derivedStateOf {
            uiState.value.showCamera
        }
    }
    val showPhotoResultState = remember {
        derivedStateOf {
            uiState.value.showPhotoResult
        }
    }
    val resultPhotoUriState = remember {
        derivedStateOf {
            uiState.value.capturedImage
        }
    }

    val showPermissionDialogState = remember {
        derivedStateOf {
            uiState.value.showPermissionDialog
        }
    }

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = {
            viewModel.onPermissionResult(
                permission = Manifest.permission.CAMERA,
                isGranted = it
            )
        }
    )
    LaunchedEffect(Unit) {
        if(!context.isPermissionGranted(Manifest.permission.CAMERA)) {
            cameraPermissionLauncher.launch(
                Manifest.permission.CAMERA
            )
        } else {
            viewModel.onPermissionResult(
                permission = Manifest.permission.CAMERA,
                isGranted = true
            )
        }

    }
    val cameraExecutor = remember {
        Executors.newSingleThreadExecutor()
    }

    DisposableEffect(Unit) {
        onDispose {
            cameraExecutor.shutdown()
        }
    }
    if (showCameraState.value) {
        CameraView(
            outputDirectory = remember(context) {
                getOutputDirectory(context = context)
            },
            executor = cameraExecutor,
            onImageCaptured = {
                Log.i("TakePhotoScreen", "Image captured: $it")
                viewModel.onImageCaptured(it)
            },
            onError = { Log.e("TakePhotoScreen", "View error:", it) }
        )
    }


    if (showPhotoResultState.value) {
        Column {
            AsyncImage(
                model = resultPhotoUriState.value,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .graphicsLayer {
                       rotationY = 180f
                    },
                filterQuality = FilterQuality.High,
            )
            Card(shape = RectangleShape) {
                Spacer(modifier = Modifier.height(10.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Button(
                        onClick = {
                            viewModel.repeatTakingPhoto()
                        }
                    ) {
                        Text(text = "Jeszcze raz")
                    }
                    Spacer(modifier = Modifier.width(15.dp))
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Kontynuuj")
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))

            }


        }
    }

    if (showPermissionDialogState.value) {
        PermissionDialog(
            permissionTextProvider = CameraPermissionTextProvider(),
            isPermanentlyDeclined = false/*TODO !shouldShowRequestPermissionRationale(
                    ci
                )*/,
            onDismiss = { viewModel.dismissDialog() },
            onOkClick = {
                viewModel.dismissDialog()
                cameraPermissionLauncher.launch(
                    Manifest.permission.CAMERA
                )
            },
            onGoToAppSettingsClick = { context.openAppSettings() })
    }
}

private fun Context.isPermissionGranted(permission: String): Boolean {
    return ContextCompat.checkSelfPermission(
        this,
        permission
    ) == PackageManager.PERMISSION_GRANTED
}

private fun getOutputDirectory(context: Context): File {
    val mediaDir = context.externalMediaDirs.firstOrNull()?.let {
        File(it, context.getString(R.string.app_name)).apply { mkdirs() }
    }

    return if (mediaDir != null && mediaDir.exists()) mediaDir else context.filesDir
}
