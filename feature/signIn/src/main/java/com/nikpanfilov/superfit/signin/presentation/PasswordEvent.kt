package com.nikpanfilov.superfit.signin.presentation

sealed class PasswordEvent {
	object NavigateBack : PasswordEvent()
	data class AddNumber(val number: String) : PasswordEvent()
}