package com.nikpanfilov.superfit.plank.presentation

sealed class PlankEvent {
	object Finish : PlankEvent()
	data class ChangeLeftCount(val left: Int) : PlankEvent()
}