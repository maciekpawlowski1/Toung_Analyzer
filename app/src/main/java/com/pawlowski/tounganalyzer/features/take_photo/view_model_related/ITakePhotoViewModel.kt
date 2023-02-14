package com.pawlowski.tounganalyzer.features.take_photo.view_model_related

import org.orbitmvi.orbit.ContainerHost

interface ITakePhotoViewModel: ContainerHost<TakePhotoUiState, TakePhotoSideEffect> {
    fun onPermissionResult(permission: String, isGranted: Boolean)
    fun dismissDialog()
    fun showResultPhoto()
}