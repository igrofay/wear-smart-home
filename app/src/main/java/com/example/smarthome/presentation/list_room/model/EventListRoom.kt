package com.example.smarthome.presentation.list_room.model

sealed class EventListRoom {
    class OpenRoom(val nameRoom:String) : EventListRoom()
}