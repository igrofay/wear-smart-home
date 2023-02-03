package com.example.smarthome.data.model.device

import com.example.smarthome.domain.device.DeviceModel
import com.example.smarthome.domain.device.TypeDevice

data class DeviceData(
    override val nameDevice: String,
    override val type: TypeDevice,
    override val valueDevice: Int = 0,
    override val isTurnedOn: Boolean = true,
) : DeviceModel
