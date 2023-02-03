package com.example.smarthome.presentation.list_room.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smarthome.domain.repos.RoomRepos
import com.example.smarthome.presentation.common.view_model.EventBase
import com.example.smarthome.presentation.list_room.model.EventListRoom
import com.example.smarthome.presentation.list_room.model.StateListRoom
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListRoomVM @Inject constructor(
    private val roomRepos: RoomRepos,
) : ViewModel(), EventBase<EventListRoom> {

    private val _state = mutableStateOf(StateListRoom())
    val state: State<StateListRoom> by ::_state

    private val job: Job = viewModelScope.launch {
        roomRepos.getListRoom().collect { list ->
            _state.value = _state.value.copy(listRoom = list)
        }
    }

    override fun onEvent(event: EventListRoom) {
        when (event) {
            is EventListRoom.OpenRoom -> {
                val room =
                    _state.value.listRoom.singleOrNull { room -> room.nameRoom == event.nameRoom }
                        ?: return
                _state.value = _state.value.copy(lastOpen = room)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }


}