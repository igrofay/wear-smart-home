package com.example.smarthome.presentation.list_room.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import com.example.smarthome.domain.room.RoomModel
import com.example.smarthome.presentation.list_room.model.imageRoom

@Composable
fun ItemRoom(
    item:RoomModel,
    onClick: ()-> Unit,
    isLastOpen: Boolean,
) {
    Column(
        modifier = Modifier
            .padding(5.dp)
            .clip(RoundedCornerShape(20))
            .background(
                if (isLastOpen) MaterialTheme.colors.secondary else Color.White
            ).clickable(onClick = onClick)
            .padding(vertical = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Icon(
            painter = item.imageRoom(),
            contentDescription = null,
            tint =  if (isLastOpen) Color.White else MaterialTheme.colors.secondary,
            modifier = Modifier.size(40.dp)
        )
        Text(
            text = item.nameRoom,
            color =  if (isLastOpen) Color.White else Color.Black,
            style = MaterialTheme.typography.caption
        )
    }
}