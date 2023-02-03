package com.example.smarthome.presentation.nav.view

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.smarthome.presentation.authentication.view.AuthenticationScreen
import com.example.smarthome.presentation.launch.view.LaunchScreen
import com.example.smarthome.presentation.nav.model.MainRouting
import com.example.smarthome.presentation.nav.model.StartRouting

fun NavGraphBuilder.startGraph(navController: NavController){
    navigation(
        startDestination = StartRouting.Launch.route,
        route = StartRouting.route
    ) {
        composable(StartRouting.Launch.route) {
            LaunchScreen(
                grantAccess = {
                    navController.navigate(MainRouting.route) {
                        popUpTo(StartRouting.Launch.route) {
                            inclusive = true
                        }
                    }
                },
                needAuthentication = {
                    navController.navigate(StartRouting.Auth.route) {
                        popUpTo(StartRouting.Launch.route) { inclusive = true }
                    }
                }
            )
        }
        composable(StartRouting.Auth.route) {
            AuthenticationScreen(
                grantAccess = {
                    navController.navigate(MainRouting.route) {
                        popUpTo(StartRouting.Auth.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}