package com.exempel.smarthouse.feature.common.view.vertical_regulator

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.core.math.MathUtils
import kotlin.math.pow

@Composable
fun VerticalRegulator(
    value: Float, // 0-1
    onValueChange: (Float)->Unit,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(10.dp),
    colors: VerticalRegulatorColors = VerticalRegulatorColors.default()
){
    BoxWithConstraints(
        modifier = modifier
            .padding(paddingValues)
    ) {
        Canvas(
            modifier = modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectDragGestures { change, _ ->
                        val v =  1 - change.position.y/maxHeight.toPx()
                        onValueChange(MathUtils.clamp(v, 0f, 1f))
                    }
                },
        ){
            val height = size.height.pow(0.6f)
            val count = (size.height / height ).toInt()
            for (i in 0 until count){
                val widthItem = size.width - 25 * i
                val heightItem = height * 0.8f
                val pad = height * 0.2f
                val position = Offset(
                    center.x - widthItem/2 ,
                    height * i + pad
                )
                if (size.height * value >= height * (count-i) ){
                    drawRect(
                        color = colors.highlightedZoneColor,
                        size = Size(widthItem,heightItem),
                        topLeft = position
                    )
                }else{
                    drawRect(
                        color = colors.strokeColor,
                        size = Size(widthItem,heightItem),
                        topLeft = position,
                        style = Stroke(2f)
                    )
                }

            }
        }
    }
}