package com.example.smarthome.data.repos

import com.example.smarthome.data.model.device.DeviceData
import com.example.smarthome.data.model.room.RoomData
import com.example.smarthome.domain.device.DeviceModel
import com.example.smarthome.domain.device.TypeDevice
import com.example.smarthome.domain.repos.RoomRepos
import com.example.smarthome.domain.room.RoomModel
import com.example.smarthome.domain.room.TypeRoom
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomReposImpl @Inject constructor() : RoomRepos{

    private val data = listOf(
        RoomData(
            "Living Room",
            TypeRoom.LivingRoom,
            mutableListOf(
                DeviceData("Light", TypeDevice.LED),
                DeviceData("Thermostat", TypeDevice.Thermostat),
            )
        ),
        RoomData(
            "Bedroom",
            TypeRoom.Bedroom,
            mutableListOf(
                DeviceData("Light", TypeDevice.LED),
            )
        ),
        RoomData(
            "Kitchen",
            TypeRoom.Kitchen,
            mutableListOf(
                DeviceData("Thermostat", TypeDevice.Thermostat),
            )
        )
    )
    override fun getListRoom(): Flow<List<RoomModel>> {
        return flow {
            delay(1_000L)
            emit(data)
        }
    }

    override fun getRoomListDevice(nameRoom: String): Flow<List<DeviceModel>> {
        return flow {
            delay(500L)
            val listDevice = data
                .single { room-> room.nameRoom == nameRoom }
            emit(listDevice.listDevice)
        }
    }

    private var deviceFlow = MutableStateFlow<DeviceModel?>(null)

    override fun getDevice(nameRoom: String, nameDevice: String): Flow<DeviceModel> {
        val deviceModel = data
            .single { room-> room.nameRoom == nameRoom }
            .listDevice.single { device-> device.nameDevice == nameDevice }
        deviceFlow.tryEmit(deviceModel)
        return deviceFlow.filterNotNull()
    }

    override fun updateDataDevice(
        nameRoom: String, deviceModel: DeviceModel
    ) {
        val listDevice = data
            .single { room-> room.nameRoom == nameRoom }.listDevice as MutableList
        val index = listDevice
            .indexOfFirst {  device-> device.nameDevice == deviceModel.nameDevice  }
        listDevice[index] = deviceModel
        deviceFlow.tryEmit(deviceModel)
    }


}