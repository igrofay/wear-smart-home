package com.example.smarthome.presentation.device_control.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smarthome.presentation.device_control.model.StateDeviceControl
import com.example.smarthome.presentation.device_control.view_model.DeviceControlVM

@Composable
fun DeviceControlScreen(
    viewModel: DeviceControlVM = hiltViewModel()
) {
    when (val state = viewModel.state.value) {
        is StateDeviceControl.Display -> {
            ControlView(
                deviceModel = state.device,
                eventBase = viewModel,
            )
        }
        StateDeviceControl.Load ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) { CircularProgressIndicator() }
    }
}