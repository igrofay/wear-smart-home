package com.example.smarthome.data.repos

import android.content.res.Resources
import com.example.smarthome.R
import com.example.smarthome.data.data_source.smart_home_api.UserApi
import com.example.smarthome.data.validation_str.TypeStr
import com.example.smarthome.domain.error.AuthenticationError
import com.example.smarthome.domain.repos.AuthRepos
import com.example.smarthome.domain.user.UserModel
import com.example.smarthome.data.validation_str.ValidationStr
import javax.inject.Inject
import javax.inject.Singleton


class AuthReposImpl @Inject constructor(
    private val resources: Resources,
    private val validationStr: ValidationStr,
    private val userApi: UserApi,
) : AuthRepos {
    override suspend fun signIn(email: String, password: String): UserModel {
        if (!validationStr.execute(TypeStr.Email(email))){
            throw AuthenticationError.EmailEnteredIncorrectly(
                resources.getString(R.string.email_entered_incorrectly)
            )
        }
        if (!validationStr.execute(TypeStr.Password(password))){
            throw AuthenticationError.PasswordEnteredIncorrectly(
                resources.getString(R.string.password_must_be_more_than_five_characters)
            )
        }
        try {
            return userApi.signIn(email, password)
        }catch (e: Exception){
            throw AuthenticationError.EmailEnteredIncorrectly(e.message!!)
        }catch (e: Error){
            throw AuthenticationError.PasswordEnteredIncorrectly(e.message!!)
        }
    }
}