package com.example.smarthome.presentation.list_room.model

import com.example.smarthome.domain.room.RoomModel
import kotlinx.coroutines.flow.Flow

data class StateListRoom(
    val listRoom: List<RoomModel> = listOf(),
    val lastOpen:RoomModel? = null,
)