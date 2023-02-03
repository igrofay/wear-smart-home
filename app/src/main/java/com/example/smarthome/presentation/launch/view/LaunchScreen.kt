package com.example.smarthome.presentation.launch.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.exempel.smarthouse.feature.launch.model.StateLaunch
import com.example.smarthome.presentation.launch.view_model.LaunchVM

@Composable
fun LaunchScreen(
    grantAccess: () -> Unit,
    needAuthentication: () -> Unit,
    viewModel: LaunchVM = hiltViewModel()
) {
    when (viewModel.state.value) {
        StateLaunch.Authorized ->
            LaunchedEffect(Unit) { grantAccess() }
        StateLaunch.Load ->
            LoadView(viewModel)
        StateLaunch.NeedAuthentication ->
            LaunchedEffect(Unit) { needAuthentication() }
    }

}