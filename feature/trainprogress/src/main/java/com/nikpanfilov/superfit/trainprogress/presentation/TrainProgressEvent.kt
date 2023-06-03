package com.nikpanfilov.superfit.trainprogress.presentation

sealed class TrainProgressEvent {
	object NavigateBack : TrainProgressEvent()
}