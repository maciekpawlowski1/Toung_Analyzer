package com.pawlowski.tounganalyzer.features.interview.ui

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.interviewNavigationPath(
    navController: NavHostController,
    onNavigateToNextScreen: () -> Unit
) {
    navigation(startDestination = "AskAboutInterview", route = "Interview") {
        composable(route = "AskAboutInterview") {
            AskAboutInterviewScreen(
                onNavigateToQuestionsScreen = {
                    navController.navigate("Questions") {
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onNavigateToNextScreen = onNavigateToNextScreen
            )
        }

        composable("Questions") {
            QuestionsScreen(
                onNavigateToOnboardingScreen = onNavigateToNextScreen
            )
        }
    }

}