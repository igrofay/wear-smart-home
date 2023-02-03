package com.example.smarthome.presentation.nav.model

sealed class MainRouting(val route: String){
    companion object{
        const val route = "main"
    }
    object ListRoom : MainRouting("list_room")
    object ListDevice : MainRouting("list_device"){
        const val arg1 = "nameRoom"
        fun allRoute(
            nameRoom: String = "{$arg1}"
        ) = "$route/$nameRoom"
    }
    object DeviceControl : MainRouting("device_control"){
        const val arg1 = "nameRoom"
        const val arg2 = "nameDevice"
        fun allRoute(
            nameRoom: String = "{$arg1}",
            nameDevice:String = "{$arg2}"
        ) = "$route/$nameRoom/$nameDevice"
    }
}