package com.pawlowski.tounganalyzer.features.take_photo.ui

import android.Manifest
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.pawlowski.tounganalyzer.R
import com.pawlowski.tounganalyzer.features.take_photo.view_model_related.ITakePhotoViewModel
import com.pawlowski.tounganalyzer.features.take_photo.view_model_related.TakePhotoViewModel
import java.io.File
import java.util.concurrent.Executors

@Composable
fun TakePhotoScreen(
    viewModel: ITakePhotoViewModel = hiltViewModel<TakePhotoViewModel>()
) {
    val uiState = viewModel.container.stateFlow.collectAsState()

    val showCamera = remember {
        derivedStateOf {
            uiState.value.showCamera
        }
    }
    val showPhotoResult = remember {
        mutableStateOf(false)
    }
    val resultPhotoUri = remember {
        mutableStateOf<Uri?>(null)
    }
    val context = LocalContext.current

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
        cameraPermissionLauncher.launch(
            Manifest.permission.CAMERA
        )
    }
    val cameraExecutor = remember {
        Executors.newSingleThreadExecutor()
    }

    DisposableEffect(Unit) {
        onDispose {
            cameraExecutor.shutdown()
        }
    }
    if (showCamera.value) {
        CameraView(
            outputDirectory = remember {
               getOutputDirectory(context = context)
            },
            executor = cameraExecutor,
            onImageCaptured = {
                Log.i("TakePhotoScreen", "Image captured: $it")
                resultPhotoUri.value = it
                viewModel.showResultPhoto()
                showPhotoResult.value = true
            },
            onError = { Log.e("TakePhotoScreen", "View error:", it) }
        )
    }

    if (showPhotoResult.value) {
        AsyncImage(
            model = resultPhotoUri.value,
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}


private fun getOutputDirectory(context: Context): File {
    val mediaDir = context.externalMediaDirs.firstOrNull()?.let {
        File(it, context.getString(R.string.app_name)).apply { mkdirs() }
    }

    return if (mediaDir != null && mediaDir.exists()) mediaDir else context.filesDir
}



/*
override fun onDestroy() {
    super.onDestroy()
    cameraExecutor.shutdown()
}*/
