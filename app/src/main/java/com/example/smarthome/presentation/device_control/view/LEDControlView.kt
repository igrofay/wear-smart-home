package com.example.smarthome.presentation.device_control.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.smarthome.domain.device.DeviceModel
import com.example.smarthome.R
import com.example.smarthome.presentation.common.view.button.CustomButton
import com.example.smarthome.presentation.common.view_model.EventBase
import com.example.smarthome.presentation.device_control.model.EventDeviceControl
import com.example.smarthome.presentation.device_control.model.StateLEDControlView

@Composable
fun LEDControlView(
    deviceModel: DeviceModel,
    eventBase: EventBase<EventDeviceControl>,
) {
    var isPlus by remember {
        mutableStateOf(StateLEDControlView.None)
    }
    Column {
        Text(
            text = "${deviceModel.valueDevice}",
            color = MaterialTheme.colors.secondary,
            style = MaterialTheme.typography.h3,
            modifier = Modifier.padding(horizontal = 12.dp).fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomButton(
                onClick = {
                    isPlus = if (isPlus == StateLEDControlView.Minus) {
                        StateLEDControlView.None
                    } else {
                        StateLEDControlView.Minus
                    }
                },
                painter = painterResource(R.drawable.ic_minus),
                modifier = Modifier
                    .size(30.dp),
                shape = CircleShape,
                enabled = isPlus == StateLEDControlView.Minus,
            )
            CustomButton(
                onClick = {
                    when (isPlus) {
                        StateLEDControlView.Plus -> {
                            eventBase.onEvent(EventDeviceControl.PlusOne)
                        }
                        StateLEDControlView.Minus -> {
                            eventBase.onEvent(EventDeviceControl.MinusOne)
                        }
                        StateLEDControlView.None -> {}
                    }
                },
                onLongClick = if (isPlus == StateLEDControlView.None) {
                    { eventBase.onEvent(EventDeviceControl.TurnOn(!deviceModel.isTurnedOn)) }
                } else null,
                painter = painterResource(R.drawable.ic_turn_on),
                modifier = Modifier.size(70.dp),
                contentPaddingValues = PaddingValues(18.dp),
                enabled = deviceModel.isTurnedOn
            )
            CustomButton(
                onClick = {
                    isPlus = if (isPlus == StateLEDControlView.Plus) {
                        StateLEDControlView.None
                    } else {
                        StateLEDControlView.Plus
                    }
                },
                painter = painterResource(R.drawable.ic_plus),
                modifier = Modifier
                    .size(30.dp),
                shape = CircleShape,
                enabled = isPlus == StateLEDControlView.Plus
            )
        }
    }
}