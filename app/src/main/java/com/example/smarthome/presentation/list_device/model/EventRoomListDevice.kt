package com.example.smarthome.presentation.list_device.model

sealed class EventRoomListDevice {
    class OpenDevice(val nameDevice: String) : EventRoomListDevice()
}