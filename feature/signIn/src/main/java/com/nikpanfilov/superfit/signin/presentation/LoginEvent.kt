package com.nikpanfilov.superfit.signin.presentation

sealed class LoginEvent {
	object NavigateToPassword : LoginEvent()
	object NavigateToSignUp : LoginEvent()
	data class LoginChange(val login: String) : LoginEvent()
}