package com.pawlowski.tounganalyzer.features.take_photo.view_model_related

import android.Manifest
import android.net.Uri
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class TakePhotoViewModel @Inject constructor(

): ITakePhotoViewModel, ViewModel() {
    override val container: Container<TakePhotoUiState, TakePhotoSideEffect>
        = container(
            initialState = TakePhotoUiState(
                showPermissionDialog = false,
                showCamera = false
            )
        )

    override fun onPermissionResult(
        permission: String,
        isGranted: Boolean
    ) = intent {
        if(!isGranted && !state.showPermissionDialog) {
            reduce {
                state.copy(showPermissionDialog = true)
            }
        }
        else if(isGranted && permission == Manifest.permission.CAMERA) {
            reduce {
                state.copy(showCamera = true)
            }
        }
    }

    override fun onImageCaptured(uri: Uri) = intent {
        reduce {
            state.copy(
                showCamera = false,
                capturedImage = uri,
                showPhotoResult = true
            )
        }
    }

    override fun repeatTakingPhoto() = intent {
        reduce {
            state.copy(
                showPhotoResult = false,
                showCamera = true,
                capturedImage = null
            )
        }
    }

    override fun dismissDialog() = intent {
        reduce {
            state.copy(showPermissionDialog = false)
        }
    }
}