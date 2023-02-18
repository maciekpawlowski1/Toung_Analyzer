package com.pawlowski.tounganalyzer.features.onboarding.ui

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.onboardingNavigationPath(
    navController: NavHostController,
    onNavigateToNextScreen: () -> Unit
) {
    navigation(startDestination = "Starting", route = "Onboarding") {
        composable(route = "Starting") {
            StartingScreen(
                onNavigateToNextScreen = {
                    navController.navigate("Description") {
                        launchSingleTop = true
                    }
                }
            )
        }

        composable("Description") {
            DescriptionScreen(
                onNavigateToNextScreen = onNavigateToNextScreen
            )
        }
    }
}