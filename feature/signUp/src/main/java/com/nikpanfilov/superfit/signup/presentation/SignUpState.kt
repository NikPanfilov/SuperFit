package com.nikpanfilov.superfit.signup.presentation

sealed class SignUpState {
	object Initial : SignUpState()
	object Loading : SignUpState()

	data class Content(
		val userName: String,
		val email: String,
		val pinCode: String
	) : SignUpState()
}
