package com.example.smarthome.presentation.authentication.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.smarthome.domain.error.AuthenticationError
import com.example.smarthome.presentation.authentication.model.EventAuthentication
import com.example.smarthome.presentation.authentication.model.StateAuthentication
import com.example.smarthome.presentation.common.view_model.EventBase

@Composable
fun DataInputView(
    dataInput: StateAuthentication.DataInput,
    errorInput: AuthenticationError?,
    event: EventBase<EventAuthentication>,
) {
    DataInputBackground {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(modifier = Modifier.weight(0.3f))
            Text(
                text = "Smart House",
                style = MaterialTheme.typography.subtitle1,
                color = Color.White
            )
            Spacer(modifier = Modifier.weight(1f))
            DataInputCard(dataInput, errorInput, event)
            Button(
                onClick = {
                    event.onEvent(EventAuthentication.Sign)
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.secondary,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth(0.9f),
                shape = MaterialTheme.shapes.medium,
                contentPadding = PaddingValues(),
                border = BorderStroke(1.dp, Color(0xFF707070))
            ) {
                Text(
                    text = "Enter Your House",
                )
            }
            Spacer(modifier = Modifier.weight(0.2f))
        }
    }
}