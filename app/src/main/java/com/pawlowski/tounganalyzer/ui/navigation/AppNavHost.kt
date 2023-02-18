package com.pawlowski.tounganalyzer.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pawlowski.tounganalyzer.features.interview.ui.interviewNavigationPath
import com.pawlowski.tounganalyzer.features.onboarding.ui.onboardingNavigationPath
import com.pawlowski.tounganalyzer.features.take_photo.ui.TakePhotoScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    isTakingPhotoPermissionPermanentlyDenied: () -> Boolean
) {
    NavHost(navController = navController, startDestination = "Onboarding") {
        onboardingNavigationPath(
            navController,
            onNavigateToNextScreen = {
                navController.navigate("TakePhoto") {
                    launchSingleTop = true
                }
            }
        )
        composable(route = "TakePhoto") {
            TakePhotoScreen(
                isTakingPhotoPermissionPermanentlyDenied = isTakingPhotoPermissionPermanentlyDenied,
                onNavigateToNextScreen = {
                    navController.navigate("Interview")
                }
            )
        }
        interviewNavigationPath(
            navController,
            onNavigateToNextScreen = {
                navController.navigate("Onboarding") {
                    launchSingleTop = true
                    popUpTo(route = "Onboarding") {
                        inclusive = true
                    }
                }
            }
        )
    }
}