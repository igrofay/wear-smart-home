package com.example.smarthome.data.repos

import androidx.datastore.core.DataStore
import com.example.smarthome.data.model.user.UserData
import com.example.smarthome.domain.error.ErrorRequest
import com.example.smarthome.domain.repos.UserRepos
import com.example.smarthome.domain.user.UserModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class UserReposImpl @Inject constructor(
    private val store: DataStore<UserData?>,
): UserRepos {
    override fun getUser(): Flow<UserModel> {
        return store.data.filterNotNull()
    }

    override suspend fun singleUser(): UserModel {
        return store.data.first()
            ?: throw ErrorRequest.DataNotFound()
    }

    override suspend fun updateUser(userModel: UserModel) {
        store.updateData {
            UserData(
                email = userModel.email,
                username = userModel.username,
                phone = userModel.phone,
                gender = userModel.gender,
                dataOfBirth = userModel.dataOfBirth,
                urlImageProfile = userModel.urlImageProfile
            )
        }
    }

    override suspend fun clearUser() {
        store.updateData { null }
    }

}
