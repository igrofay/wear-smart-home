package com.example.smarthome.presentation.common.view.dimmer

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.*
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.math.MathUtils
import kotlin.math.*

@Composable
fun Dimmer(
    value: Int, // 0 - 199
    onValueChange: (Int)->Unit,
    modifier: Modifier = Modifier,
    colors: DimmerColors = DimmerColors.default(),
    paddingValues: PaddingValues = PaddingValues(10.dp),
    stroke: Dp = 10.dp,
    sizePoint: Dp = 15.dp,
    strokeLine: Dp = 2.dp,
    textSize: TextUnit = 14.sp,
    maxValues: Int = 199,
    minValues: Int = 0
) {

    var shapeCenter by remember {
        mutableStateOf(Offset.Zero)
    }
    var handleCenter by remember {
        mutableStateOf(Offset.Zero)
    }
    var angle by remember {
        mutableStateOf((value+50)*1.8)
    }
    BoxWithConstraints(
        modifier = modifier
            .padding(paddingValues)
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    angle = MathUtils.clamp(
                        getRotationAngle(
                            handleCenter + dragAmount,
                            shapeCenter
                        ),
                        (minValues+50)*1.8,
                        (maxValues+50)*1.8,
                    )
                    val v = (angle / 1.8).toInt()
                    if(v - 50<0){
                        onValueChange(200+v-50)
                    }else{
                        onValueChange(v-50)
                    }
                    change.consume()
                }
            }
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize(),
        ){
            val radius =  size.minDimension / 2f  - sizePoint.toPx() * 1.75f
            val radiusDimmer = radius - sizePoint.toPx() * 1.75f
            shapeCenter = center
            val x = (center.x + cos(Math.toRadians(angle)) * radius).toFloat()
            val y = (center.y + sin(Math.toRadians(angle)) * radius).toFloat()
            handleCenter = Offset(x, y)

            drawCircle(
                color = colors.areaLimitationColor,
                style = Stroke(stroke.toPx()),
            )
            drawCircle(
                color = colors.mainColor,
                style = Stroke(stroke.toPx()),
                radius = radius
            )
            dimmerVisualization(
                radiusDimmer= radiusDimmer,
                stroke=  stroke,
                strokeLine= strokeLine,
                colors = colors,
                textSize = textSize
            )
            point(
                handleCenter = handleCenter,
                sizePoint = sizePoint,
                colors = colors
            )
        }
    }
}


private fun getRotationAngle(currentPosition: Offset, center: Offset): Double {
    val (dx, dy) = currentPosition - center
    val theta = atan2(dy, dx).toDouble()

    var angle = Math.toDegrees(theta)

    if (angle < 0) {
        angle += 360.0
    }
    return angle
}
