package com.example.smarthome.presentation.list_device.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smarthome.presentation.list_device.model.EventRoomListDevice
import com.example.smarthome.presentation.list_device.view_model.RoomListDeviceVM

@Composable
fun RoomListDeviceScreen(
    openDevice: (nameDevice:String) -> Unit,
    viewModel: RoomListDeviceVM = hiltViewModel()
) {
    val state by viewModel.state
    Column {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Devices",
                color = MaterialTheme.colors.secondary,
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier.padding(vertical = 6.dp)
            )
            Divider(color = MaterialTheme.colors.secondary)
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.Center,
        ){
            items(state.listDevice){ deviceModel->
                ItemDevice(
                    item = deviceModel,
                    onClick = {
                        viewModel.onEvent(EventRoomListDevice.OpenDevice(deviceModel.nameDevice))
                        openDevice(deviceModel.nameDevice)
                    },
                    isLastOpen = deviceModel == state.lastOpen
                )
            }
        }
    }
}