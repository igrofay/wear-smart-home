package com.example.smarthome.domain.user


interface UserModel{
    val urlImageProfile : String
    val username: String
    val email: String
    val phone: String
    val gender: Gender
    val dataOfBirth: String
}
