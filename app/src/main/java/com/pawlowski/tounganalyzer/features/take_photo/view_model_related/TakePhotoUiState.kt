package com.pawlowski.tounganalyzer.features.take_photo.view_model_related

import android.net.Uri

data class TakePhotoUiState(
    val showPermissionDialog: Boolean = false,
    val showCamera: Boolean,
    val capturedImage: Uri? = null,
    val showPhotoResult: Boolean = false,
)
