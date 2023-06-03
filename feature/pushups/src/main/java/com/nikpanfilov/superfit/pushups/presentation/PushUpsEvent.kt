package com.nikpanfilov.superfit.pushups.presentation

sealed class PushUpsEvent {
	object Finish : PushUpsEvent()
	object Done : PushUpsEvent()
}