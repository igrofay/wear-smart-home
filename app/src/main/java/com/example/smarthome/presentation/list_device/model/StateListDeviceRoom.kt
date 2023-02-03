package com.example.smarthome.presentation.list_device.model

import com.example.smarthome.domain.device.DeviceModel

data class StateListDeviceRoom(
    val listDevice: List<DeviceModel> = listOf(),
    val lastOpen: DeviceModel? = null
)