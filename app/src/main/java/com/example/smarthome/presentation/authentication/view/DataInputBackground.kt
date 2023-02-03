package com.example.smarthome.presentation.authentication.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.smarthome.R
import com.example.smarthome.presentation.common.view.theme.darkYellow

@Composable
fun DataInputBackground(
    content: @Composable ()->Unit
) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Image(
            painter = painterResource(R.drawable.im_street),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .drawWithContent {
                drawContent()
                drawRect(
                    color = darkYellow.copy(0.57f),
                    size = size
                )
            }
        )
        content()
    }
}