package com.example.smarthome.domain.repos

import com.example.smarthome.domain.device.DeviceModel
import com.example.smarthome.domain.room.RoomModel
import kotlinx.coroutines.flow.Flow

interface RoomRepos {

    fun getListRoom(): Flow<List<RoomModel>>

    fun getRoomListDevice(nameRoom: String): Flow<List<DeviceModel>>
    fun getDevice(nameRoom: String, nameDevice:String) : Flow<DeviceModel>
    fun updateDataDevice(nameRoom:String, deviceModel: DeviceModel)
}