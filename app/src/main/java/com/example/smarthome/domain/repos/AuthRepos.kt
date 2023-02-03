package com.example.smarthome.domain.repos

import com.example.smarthome.domain.user.UserModel

interface AuthRepos {
    suspend fun signIn(email:String, password:String): UserModel
}