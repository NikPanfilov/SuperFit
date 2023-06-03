package com.nikpanfilov.superfit.signin.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class LoginViewModel(private val router: LoginRouter) : ViewModel() {

	val uiState = MutableStateFlow(LoginState(""))

	fun handleEvent(event: LoginEvent) {
		when (event) {
			is LoginEvent.NavigateToPassword -> router.navigateToPassword(uiState.value.login)
			LoginEvent.NavigateToSignUp      -> router.navigateToSignUp()
			is LoginEvent.LoginChange        -> uiState.value = LoginState(event.login)
		}
	}
}