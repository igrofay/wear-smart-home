package com.example.smarthome.presentation.common.view.dimmer

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp

fun DrawScope.point(
    handleCenter: Offset,
    sizePoint: Dp,
    colors: DimmerColors,
) {
    drawCircle(
        color = Color.Black.copy(0.15f),
        center = handleCenter,
        radius = sizePoint.toPx()*1.1f,
    )
    drawCircle(
        color = colors.strokeColor,
        center = handleCenter,
        radius = sizePoint.toPx(),
    )
    drawCircle(
        color = colors.mainColor,
        center = handleCenter,
        radius = sizePoint.toPx()*0.65f,
    )
}