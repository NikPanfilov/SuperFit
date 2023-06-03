package com.nikpanfilov.superfit.signup.presentation

sealed class SignUpEvent {
	object SignUp : SignUpEvent()
	object NavigateToSignIn : SignUpEvent()
	data class UsernameChange(val username: String) : SignUpEvent()
	data class EmailChange(val email: String) : SignUpEvent()
	data class CodeChange(val code: String) : SignUpEvent()
}