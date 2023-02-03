package com.exempel.smarthouse.feature.common.view.vertical_regulator

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color



data class VerticalRegulatorColors(
    val highlightedZoneColor: Color,
    val strokeColor: Color,
){
    companion object{
        @Composable
        fun default() = VerticalRegulatorColors(
            highlightedZoneColor = MaterialTheme.colors.secondary,
            strokeColor = Color.Gray
        )
    }
}
