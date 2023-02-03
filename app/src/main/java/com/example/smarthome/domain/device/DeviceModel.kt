package com.example.smarthome.domain.device

interface DeviceModel {
    val type: TypeDevice
    val valueDevice: Int
    val nameDevice: String
    val isTurnedOn: Boolean
}