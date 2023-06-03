package com.nikpanfilov.superfit.running.presentation

sealed class RunningState {
	object Initial : RunningState()

	data class Content(val left: Int) : RunningState()
}