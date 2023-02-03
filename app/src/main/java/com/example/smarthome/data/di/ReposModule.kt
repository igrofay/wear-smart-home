package com.example.smarthome.data.di

import com.example.smarthome.data.repos.AuthReposImpl
import com.example.smarthome.data.repos.RoomReposImpl
import com.example.smarthome.data.repos.UserReposImpl
import com.example.smarthome.domain.repos.AuthRepos
import com.example.smarthome.domain.repos.RoomRepos
import com.example.smarthome.domain.repos.UserRepos
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ReposModule {

    @Binds
    @Singleton
    abstract fun userRepos(weatherReposImpl: UserReposImpl) : UserRepos

    @Binds
    abstract fun authRepos(authRepos: AuthReposImpl): AuthRepos

    @Binds
    abstract fun roomRepos(roomRepos: RoomReposImpl): RoomRepos
}