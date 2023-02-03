package com.example.smarthome.presentation.nav.view

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.smarthome.presentation.nav.model.StartRouting

@Composable
fun InitNav() {
    val nav = rememberNavController()
    NavHost(navController = nav, startDestination = StartRouting.route) {
        startGraph(nav)
        mainGraph(nav)
    }
}