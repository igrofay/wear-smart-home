package com.example.smarthome.data.model.user

import com.example.smarthome.domain.user.Gender
import com.example.smarthome.domain.user.UserModel
import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    override val username: String = "",
    override val email: String = "",
    override val phone: String = "",
    override val gender: Gender = Gender.Male,
    override val dataOfBirth: String = "",
    override val urlImageProfile: String = "",
) : UserModel