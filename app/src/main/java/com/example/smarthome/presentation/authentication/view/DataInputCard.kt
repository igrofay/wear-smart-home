package com.example.smarthome.presentation.authentication.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.smarthome.domain.error.AuthenticationError
import com.example.smarthome.presentation.authentication.model.EventAuthentication
import com.example.smarthome.presentation.authentication.model.StateAuthentication
import com.example.smarthome.presentation.common.view.edit_text.EditText
import com.example.smarthome.presentation.common.view_model.EventBase

@Composable
fun DataInputCard(
    dataInput: StateAuthentication.DataInput,
    errorInput: AuthenticationError?,
    event: EventBase<EventAuthentication>,
) {
   Column(
       modifier = Modifier
   ) {
       EditText(
           value = dataInput.email,
           onValueChange = { event.onEvent(EventAuthentication.InputEmail(it)) },
           label = "E-mail",
           textStyle =  MaterialTheme.typography.body1
               .copy(color = Color.White),
           modifier = Modifier.fillMaxWidth(0.9f),
           isError = errorInput is AuthenticationError.EmailEnteredIncorrectly,
           keyboardOptions = KeyboardOptions(
               keyboardType = KeyboardType.Email,
           ),
       )
       Spacer(modifier = Modifier.height(10.dp))
       EditText(
           value = dataInput.password,
           onValueChange = { event.onEvent(EventAuthentication.InputPassword(it)) },
           label = "Password",
           textStyle =  MaterialTheme.typography.body1.copy(color = Color.White),
           modifier = Modifier.fillMaxWidth(0.9f),
           keyboardOptions = KeyboardOptions(
               keyboardType = KeyboardType.Password,
           ),
           visualTransformation = PasswordVisualTransformation(mask = '▌'),
           isError = errorInput is AuthenticationError.PasswordEnteredIncorrectly,
       )
   }
}