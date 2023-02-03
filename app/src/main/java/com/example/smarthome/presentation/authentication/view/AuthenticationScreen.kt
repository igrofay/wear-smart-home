package com.example.smarthome.presentation.authentication.view

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smarthome.domain.error.AuthenticationError
import com.example.smarthome.presentation.authentication.model.StateAuthentication
import com.example.smarthome.presentation.authentication.view_model.AuthenticationVM

@Composable
fun AuthenticationScreen(
    grantAccess: () -> Unit,
    viewModel: AuthenticationVM = hiltViewModel()
) {
    val error = viewModel.stateErrorMessage.value
    val context = LocalContext.current
    LaunchedEffect(error){
        viewModel.stateErrorMessage
            .value?.message?.let { message ->
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
    }
    when(
        val state = viewModel.state.value
    ){
        StateAuthentication.Authorized -> LaunchedEffect(Unit) { grantAccess() }
        is StateAuthentication.DataInput -> DataInputView(
            state,
            error as? AuthenticationError,
            viewModel,
        )
    }
}