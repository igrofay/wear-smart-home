package com.exempel.smarthouse.feature.launch.model

sealed class StateLaunch{
    object Load : StateLaunch()
    object Authorized : StateLaunch()
    object NeedAuthentication : StateLaunch()
}
