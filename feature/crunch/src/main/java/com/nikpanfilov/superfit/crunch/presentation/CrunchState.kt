package com.nikpanfilov.superfit.crunch.presentation

sealed class CrunchState {
	object Initial : CrunchState()

	data class Content(val left: Int) : CrunchState()
}