package com.example.smarthome.presentation.common.view.button

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomButton(
    onClick: ()->Unit,
    painter: Painter,
    modifier: Modifier = Modifier,
    onLongClick: (()->Unit)? =null,
    shape: Shape = RoundedCornerShape(10),
    enabled: Boolean = true,
    contentPaddingValues: PaddingValues = PaddingValues(6.dp)
) {
    val colorBackground by animateColorAsState(
        targetValue = if (enabled) MaterialTheme.colors.secondary else Color(0xFFF0EFF5),
        animationSpec = tween(durationMillis = 200, easing = LinearEasing),
    )
    val colorContent by animateColorAsState(
        targetValue = if (enabled) Color.White else Color.Black,
        animationSpec = tween(durationMillis = 200, easing = LinearEasing),
    )
    Box(
        modifier = modifier
            .clip(shape)
            .combinedClickable(onClick = onClick, onLongClick = onLongClick)
            .background(colorBackground)
            .padding(contentPaddingValues),
        contentAlignment = Alignment.Center
    ){
        Icon(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            tint = colorContent
        )
    }
}