package com.nikpanfilov.superfit.exercises.presentation

import com.nikpanfilov.shared.trainings.TrainingType

sealed class ExercisesEvent {

	object NavigateBack : ExercisesEvent()
	data class NavigateToExercise(val type: TrainingType) : ExercisesEvent()
}
