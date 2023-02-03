package com.example.smarthome.presentation.launch.view

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.min
import com.example.smarthome.R
import com.example.smarthome.presentation.common.view_model.EventBase
import com.exempel.smarthouse.feature.launch.model.EventLaunch

@Composable
fun LoadView(
    eventBase: EventBase<EventLaunch>
) {
    var targetValue by remember {
        mutableStateOf(1f)
    }
    LaunchedEffect(Unit){
        targetValue = 0f
    }
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ){
        val minSize = min(maxHeight,maxWidth) / 1.5f
        val animationRotation by animateFloatAsState(
            targetValue = targetValue,
            animationSpec = tween(
                easing = LinearEasing,
                durationMillis = 3_000,
            ),
            finishedListener = {eventBase.onEvent(EventLaunch.AnimationEnd)}
        )
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .size(minSize)
                .scale(animationRotation)
                .alpha(animationRotation)
        )
    }
}
