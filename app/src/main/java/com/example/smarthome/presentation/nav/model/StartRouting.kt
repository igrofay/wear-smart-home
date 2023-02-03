package com.example.smarthome.presentation.nav.model

sealed class StartRouting(val route:String){
    companion object{
        const val route = "start"
    }
    object Launch : StartRouting("launch")
    object Auth : StartRouting("auth")
}