package com.nikpanfilov.superfit.crunch.presentation

sealed class CrunchEvent {
	object Finish : CrunchEvent()
}