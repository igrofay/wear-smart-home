package com.example.smarthome.presentation.launch.view_model

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smarthome.domain.error.ErrorRequest
import com.example.smarthome.domain.repos.UserRepos
import com.example.smarthome.presentation.common.view_model.EventBase
import com.exempel.smarthouse.feature.launch.model.EventLaunch
import com.exempel.smarthouse.feature.launch.model.StateLaunch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchVM @Inject constructor(
    private val userRepos: UserRepos,
) : ViewModel(), EventBase<EventLaunch> {
    private val _state = mutableStateOf<StateLaunch>(StateLaunch.Load)
    val state: State<StateLaunch> by ::_state

    private fun error(e: Throwable){
        when(e){
            is ErrorRequest.DataNotFound ->{
                _state.value = StateLaunch.NeedAuthentication
            }
            else -> Log.e("LaunchVM::", e.toString())
        }
    }
    override fun onEvent(event: EventLaunch) {
        when(event){
            EventLaunch.AnimationEnd -> checkStateUserInApp()
        }
    }
    private fun checkStateUserInApp(){
        viewModelScope.launch {
            try {
                userRepos.singleUser()
                _state.value = StateLaunch.Authorized
            }catch (e: Exception){
                error(e)
            }
        }
    }
}