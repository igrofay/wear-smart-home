package com.example.smarthome.presentation.device_control.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.smarthome.domain.device.DeviceModel
import com.example.smarthome.domain.device.TypeDevice
import com.example.smarthome.presentation.common.view_model.EventBase
import com.example.smarthome.presentation.device_control.model.EventDeviceControl

@Composable
fun ControlView(
    deviceModel: DeviceModel,
    eventBase: EventBase<EventDeviceControl>,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = deviceModel.nameDevice,
                color = MaterialTheme.colors.secondary,
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier.padding(vertical = 6.dp)
            )
            Divider(color = MaterialTheme.colors.secondary)
        }
        when(deviceModel.type){
            TypeDevice.LED -> LEDControlView(deviceModel, eventBase,)
            TypeDevice.Thermostat -> ThermostatControlView(deviceModel, eventBase,)
        }
    }
}