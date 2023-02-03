package com.example.smarthome.presentation.common.view.edit_text

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun EditText(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = LocalTextStyle.current,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    val style = if (isError) textStyle.copy(color = MaterialTheme.colors.error) else textStyle
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = style,
        keyboardOptions = keyboardOptions,
        cursorBrush = SolidColor(style.color),
        visualTransformation = visualTransformation,
        singleLine = true,
    ) { innerTextField ->
        Column(
            modifier = modifier
        ) {
            Box(
                contentAlignment = Alignment.CenterStart
            ) {
                if (value.isEmpty()) {
                    Text(text = label, style = style)
                }
                innerTextField()
            }
            Divider(
                color = style.color,
                modifier = Modifier.padding(vertical = 2.dp),
                thickness = 2.dp
            )
        }
    }
}