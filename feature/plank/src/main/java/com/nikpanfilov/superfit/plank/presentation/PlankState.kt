package com.nikpanfilov.superfit.plank.presentation

sealed class PlankState {
	object Initial : PlankState()

	data class Content(val left: Int) : PlankState()
}