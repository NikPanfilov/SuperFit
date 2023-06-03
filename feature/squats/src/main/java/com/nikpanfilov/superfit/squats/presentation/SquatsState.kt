package com.nikpanfilov.superfit.squats.presentation

sealed class SquatsState {
	object Initial : SquatsState()

	data class Content(val left: Int) : SquatsState()
}