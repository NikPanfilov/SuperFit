package com.nikpanfilov.superfit.pushups.presentation

sealed class PushUpsState {
	object Initial : PushUpsState()

	data class Content(val left: Int) : PushUpsState()
}