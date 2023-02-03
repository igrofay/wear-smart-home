package com.example.smarthome.presentation.device_control.model

sealed class EventDeviceControl {
    object PlusOne : EventDeviceControl()
    object MinusOne : EventDeviceControl()
    class TurnOn(val boolean: Boolean) : EventDeviceControl()
    class ChangeValue(val value:Int): EventDeviceControl()
}