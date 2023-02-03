package com.example.smarthome.domain.use_case.auth

import com.example.smarthome.domain.repos.AuthRepos
import com.example.smarthome.domain.repos.UserRepos
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val authRepos: AuthRepos,
    private val userRepos: UserRepos,
) {
    suspend fun execute(email:String, password: String) = runCatching {
        val model = authRepos.signIn(email, password)
        userRepos.updateUser(model)
    }
}