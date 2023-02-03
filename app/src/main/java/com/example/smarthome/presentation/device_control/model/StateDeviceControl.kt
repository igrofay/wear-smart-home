package com.example.smarthome.presentation.device_control.model

import com.example.smarthome.domain.device.DeviceModel

sealed class StateDeviceControl {
    object Load : StateDeviceControl()
    data class Display(
        val device: DeviceModel,
    ): StateDeviceControl()
}