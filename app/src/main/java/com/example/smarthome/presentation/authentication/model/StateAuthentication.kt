package com.example.smarthome.presentation.authentication.model

sealed class StateAuthentication{
    object Authorized : StateAuthentication()
    data class DataInput(
        val email: String = "",
        val password: String = "",
        val type: InputType = InputType.SignIn,
        val username: String = "",
    ): StateAuthentication() {
        enum class InputType{ SignIn }
    }
}