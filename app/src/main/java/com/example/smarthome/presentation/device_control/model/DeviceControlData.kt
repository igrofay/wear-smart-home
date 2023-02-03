package com.example.smarthome.presentation.device_control.model

import com.example.smarthome.domain.device.DeviceModel
import com.example.smarthome.domain.device.TypeDevice

data class DeviceControlData(
    override var type: TypeDevice,
    override var valueDevice: Int,
    override var nameDevice: String,
    override var isTurnedOn: Boolean,
) : DeviceModel {
    companion object{
        fun copyFrom(deviceModel: DeviceModel) = DeviceControlData(
            type = deviceModel.type,
            valueDevice = deviceModel.valueDevice,
            nameDevice = deviceModel.nameDevice,
            isTurnedOn = deviceModel.isTurnedOn,
        )
    }
}