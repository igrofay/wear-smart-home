package com.example.smarthome.presentation.list_device.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.smarthome.R
import com.example.smarthome.domain.device.DeviceModel
import com.example.smarthome.domain.device.TypeDevice

@Composable
fun DeviceModel.imageDevice() : Painter{
    return when(this.type){
        TypeDevice.LED -> painterResource(R.drawable.ic_led)
        TypeDevice.Thermostat -> painterResource(R.drawable.ic_thermostat)
    }
}