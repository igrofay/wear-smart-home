package com.example.smarthome.data.model.room

import com.example.smarthome.domain.device.DeviceModel
import com.example.smarthome.domain.room.RoomModel
import com.example.smarthome.domain.room.TypeRoom
import kotlinx.serialization.Serializable

@Serializable
data class RoomData(
    override val nameRoom: String,
    override val typeRoom: TypeRoom,
    val listDevice: List<DeviceModel>,
) :RoomModel