package com.example.smarthome.presentation.list_room.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.smarthome.R
import com.example.smarthome.domain.room.RoomModel
import com.example.smarthome.domain.room.TypeRoom

@Composable
fun RoomModel.imageRoom(): Painter{
    return when(this.typeRoom){
        TypeRoom.Kitchen -> painterResource(R.drawable.ic_kitchen)
        TypeRoom.Bedroom ->painterResource(R.drawable.ic_bedroom)
        TypeRoom.LivingRoom -> painterResource(R.drawable.ic_living_room)
    }
}