package com.example.smarthome.presentation.authentication.model

sealed class EventAuthentication {
//    object ChangeLoginType: EventAuthentication()
    class InputEmail(val value:String) : EventAuthentication()
    class InputPassword(val value:String) : EventAuthentication()
    class InputUsername(val value:String) : EventAuthentication()
    object Sign: EventAuthentication()
}