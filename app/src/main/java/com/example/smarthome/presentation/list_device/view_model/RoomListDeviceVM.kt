package com.example.smarthome.presentation.list_device.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smarthome.domain.repos.RoomRepos
import com.example.smarthome.presentation.common.view_model.EventBase
import com.example.smarthome.presentation.list_device.model.EventRoomListDevice
import com.example.smarthome.presentation.list_device.model.StateListDeviceRoom
import com.example.smarthome.presentation.nav.model.MainRouting
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomListDeviceVM @Inject constructor(
    private val roomRepos: RoomRepos,
    savedStateHandle: SavedStateHandle,
) : ViewModel(), EventBase<EventRoomListDevice> {
    private val _state = mutableStateOf(StateListDeviceRoom())
    val state: State<StateListDeviceRoom> by ::_state
    private val job: Job = viewModelScope.launch {
        val nameRoom = savedStateHandle
            .get<String>(MainRouting.ListDevice.arg1)!!
        roomRepos.getRoomListDevice(nameRoom).collect { list ->
            _state.value = _state.value.copy(listDevice = list)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    override fun onEvent(event: EventRoomListDevice) {
        when (event) {
            is EventRoomListDevice.OpenDevice -> {
                val device = _state.value
                    .listDevice.single { device -> device.nameDevice == event.nameDevice }
                _state.value = _state.value.copy(lastOpen = device)
            }
        }
    }
}