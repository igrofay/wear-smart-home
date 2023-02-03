package com.example.smarthome.presentation.common.view.dimmer

import android.graphics.Paint
import android.graphics.Typeface

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

fun DrawScope.dimmerVisualization(
    radiusDimmer: Float,
    stroke: Dp,
    strokeLine: Dp,
    textSize: TextUnit,
    colors: DimmerColors,
){
    val paint = Paint().apply {
        this.textSize = textSize.toPx()
        color = colors.textColor.toArgb()
        textAlign = Paint.Align.CENTER
        typeface = Typeface.DEFAULT_BOLD
    }
    drawCircle(
        color = colors.dimmerColor,
        radius = radiusDimmer
    )
    for (i in 0 until 200 ) {
        rotate(i * 1.8f) {
            drawLine(
                color = colors.lineColor,
                start = Offset(
                    size.width / 2f,
                    size.height / (if (i%5 == 0) 4.5f else 5.5f)
                ),
                end = Offset(
                    size.width / 2f,
                    size.height / 2f - radiusDimmer
                ),
                strokeWidth = strokeLine.toPx()
            )
        }
    }
    for (i in (5 until 205) step 25){
        rotate(i * 45f - 36f){
            drawIntoCanvas {
                it.nativeCanvas.drawText(
                    i.toString(),
                    size.width / 2f,
                    size.height / 3.8f,
                    paint
                )
            }
        }
    }
    drawCircle(
        color = colors.strokeColor,
        style = Stroke(stroke.toPx()),
        radius = radiusDimmer/2.5f
    )
}