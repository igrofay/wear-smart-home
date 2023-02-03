package com.example.smarthome.domain.repos

import com.example.smarthome.domain.user.UserModel
import kotlinx.coroutines.flow.Flow

interface UserRepos {
    fun getUser(): Flow<UserModel>
    suspend fun singleUser(): UserModel
    suspend fun updateUser(userModel: UserModel)
    suspend fun clearUser()
}