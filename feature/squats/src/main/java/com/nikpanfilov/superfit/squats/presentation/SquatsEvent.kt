package com.nikpanfilov.superfit.squats.presentation

sealed class SquatsEvent {
	object NavigateBack : SquatsEvent()
	object Done : SquatsEvent()
}