package com.example.smarthome.presentation.authentication.view_model

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smarthome.domain.error.ErrorModel
import com.example.smarthome.domain.use_case.auth.SignInUseCase
import com.example.smarthome.presentation.authentication.model.EventAuthentication
import com.example.smarthome.presentation.authentication.model.StateAuthentication
import com.example.smarthome.presentation.common.view_model.EventBase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.smarthome.presentation.authentication.model.StateAuthentication.DataInput.InputType.SignIn

@HiltViewModel
class AuthenticationVM @Inject constructor(
    private val signInUseCase: SignInUseCase,
): ViewModel(), EventBase<EventAuthentication> {
    private val _state = mutableStateOf<StateAuthentication>(StateAuthentication.DataInput())
    val state: State<StateAuthentication> by ::_state
    private val _stateErrorMessage = mutableStateOf<ErrorModel?>(null)
    val stateErrorMessage : State<ErrorModel?> by ::_stateErrorMessage

    override fun onEvent(event: EventAuthentication) {
        when(event){
            is EventAuthentication.InputEmail -> {
                val value = (_state.value as? StateAuthentication.DataInput) ?: return
                _state.value = value.copy(email = event.value)
            }
            is EventAuthentication.InputPassword -> {
                val value = (_state.value as? StateAuthentication.DataInput) ?: return
                _state.value = value.copy(password = event.value)
            }
            is EventAuthentication.InputUsername -> {
                val value = (_state.value as? StateAuthentication.DataInput) ?: return
                _state.value = value.copy(username = event.value)
            }
            EventAuthentication.Sign -> viewModelScope.launch { sign() }
        }
    }
    private suspend fun sign(){
        val value = (_state.value as? StateAuthentication.DataInput) ?: return
        when( value.type){
            SignIn -> signInUseCase.execute(value.email, value.password)
        }.onSuccess {
            _state.value = StateAuthentication.Authorized
        }.onFailure(::errorVM)
    }

    private fun errorVM(e: Throwable){
        when(e){
            is ErrorModel ->{
                _stateErrorMessage.value = e
            }
            else -> Log.e("AuthenticationVM::", e.toString())
        }
    }
}