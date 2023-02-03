package com.example.smarthome.presentation.device_control.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smarthome.domain.device.DeviceModel
import com.example.smarthome.domain.device.TypeDevice
import com.example.smarthome.domain.repos.RoomRepos
import com.example.smarthome.presentation.common.view_model.EventBase
import com.example.smarthome.presentation.device_control.model.DeviceControlData
import com.example.smarthome.presentation.device_control.model.EventDeviceControl
import com.example.smarthome.presentation.device_control.model.StateDeviceControl
import com.example.smarthome.presentation.nav.model.MainRouting
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeviceControlVM @Inject constructor(
    private val roomRepos: RoomRepos,
    savedStateHandle: SavedStateHandle,
): ViewModel(), EventBase<EventDeviceControl> {
    private val _state = mutableStateOf<StateDeviceControl>(StateDeviceControl.Load)
    val state :State<StateDeviceControl> by ::_state
    private val nameRoom = savedStateHandle
        .get<String>(MainRouting.DeviceControl.arg1)!!
    private val nameDevice = savedStateHandle
        .get<String>(MainRouting.DeviceControl.arg2)!!
    private val job = viewModelScope.launch {
        roomRepos.getDevice(nameRoom,nameDevice).collect{device ->
            _state.value = StateDeviceControl.Display(device)
        }
    }


    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    override fun onEvent(event: EventDeviceControl) {
        when(event){
            EventDeviceControl.MinusOne -> {
                val data = getDeviceControlData()?.apply {
                    valueDevice -= 1
                } ?: return
                if(data.valueDevice < 0) return
                roomRepos.updateDataDevice(
                    nameRoom = nameRoom,
                    deviceModel = data
                )
            }
            EventDeviceControl.PlusOne -> {
                val data = getDeviceControlData()?.apply {
                    valueDevice += 1
                } ?: return
                if(data.valueDevice > 100) return
                roomRepos.updateDataDevice(
                    nameRoom = nameRoom,
                    deviceModel = data
                )
            }
            is EventDeviceControl.TurnOn -> {
                val data = getDeviceControlData()?.apply {
                    isTurnedOn = event.boolean
                } ?: return
                roomRepos.updateDataDevice(
                    nameRoom = nameRoom,
                    deviceModel = data
                )
            }
            is EventDeviceControl.ChangeValue -> {
                val data = getDeviceControlData()?.apply {
                    valueDevice = event.value
                } ?: return
                roomRepos.updateDataDevice(
                    nameRoom = nameRoom,
                    deviceModel = data
                )
            }
        }
    }

    private fun getDeviceControlData(): DeviceControlData?{
        val deviceModel = (_state.value as? StateDeviceControl.Display)?.device
            ?: return null
        return DeviceControlData.copyFrom(deviceModel)
    }


}