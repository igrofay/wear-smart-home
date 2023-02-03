package com.example.smarthome.domain.error

sealed class ErrorRequest: ErrorModel() {
    class DataNotFound(override val message: String? = null) : ErrorRequest()
}
