package com.example.smarthome.presentation.common.view_model

interface EventBase<T>{
    fun onEvent(event: T)
}