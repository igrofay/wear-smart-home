package com.example.smarthome.presentation.list_room.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smarthome.presentation.list_room.model.EventListRoom
import com.example.smarthome.presentation.list_room.view_model.ListRoomVM

@Composable
fun ListRoomScreen(
    openRoom: (nameRoom: String)->Unit,
    viewModel: ListRoomVM = hiltViewModel()
) {
    val state by viewModel.state
    Column {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Rooms",
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
            items(state.listRoom){ roomModel->
                ItemRoom(
                    item = roomModel,
                    onClick = {
                        viewModel.onEvent(EventListRoom.OpenRoom(roomModel.nameRoom))
                        openRoom.invoke(roomModel.nameRoom)
                    },
                    isLastOpen = roomModel == state.lastOpen
                )
            }
        }
    }
    
}