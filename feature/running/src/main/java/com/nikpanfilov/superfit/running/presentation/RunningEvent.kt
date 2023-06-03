package com.nikpanfilov.superfit.running.presentation

sealed class RunningEvent {
	object Finish : RunningEvent()
	data class ChangeLeftCount(val done: Int) : RunningEvent()
}