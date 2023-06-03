package com.nikpanfilov.superfit.trainprogress.presentation

import com.nikpanfilov.shared.trainings.TrainingType

sealed class TrainProgressState {
	object Initial : TrainProgressState()

	data class Content(
		val trainings: Map<TrainingType, Progress>
	) : TrainProgressState()
}

data class Progress(
	val last: Int?,
	val progress: Int?
)