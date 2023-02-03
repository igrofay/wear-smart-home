package com.example.smarthome.presentation.device_control.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smarthome.domain.device.DeviceModel
import com.example.smarthome.presentation.common.view.dimmer.Dimmer
import com.example.smarthome.presentation.common.view_model.EventBase
import com.example.smarthome.presentation.device_control.model.EventDeviceControl

@Composable
fun ThermostatControlView(
    deviceModel: DeviceModel,
    eventBase: EventBase<EventDeviceControl>,
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Dimmer(
            value = deviceModel.valueDevice,
            onValueChange = { eventBase.onEvent(EventDeviceControl.ChangeValue(it)) },
            stroke = 5.dp,
            sizePoint = 7.5.dp,
            strokeLine = 1.dp,
            textSize = 6.sp,
            modifier = Modifier.offset(x = maxWidth / 2f),
            maxValues = 100
        )
        Column(
            modifier = Modifier
                .width(maxWidth / 1.5f)
                .fillMaxHeight()
        ){
            Text(
                text = "${deviceModel.valueDevice}",
                color = MaterialTheme.colors.secondary,
                style = MaterialTheme.typography.h3,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            Text(
                buildAnnotatedString {
                    withStyle(
                        MaterialTheme.typography.caption.copy(
                            color = Color(0xFF898A8D),
                        ).toSpanStyle()
                    ) {
                        append("Temperature,")
                    }
                    withStyle(
                        MaterialTheme.typography.caption.copy(
                            fontWeight = FontWeight.W400,
                            color = Color(0xFF898A8D)
                        ).toSpanStyle()
                    ) {
                        append("℃")
                    }
                },
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterStart
            ){
                Switch(
                    checked = deviceModel.isTurnedOn,
                    onCheckedChange = {eventBase.onEvent(EventDeviceControl.TurnOn(it))},
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = MaterialTheme.colors.secondary
                    )
                )
            }
        }
    }
}