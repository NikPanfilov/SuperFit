package com.nikpanfilov.superfit.main.presentation

import com.nikpanfilov.shared.trainings.TrainingType

sealed class MainEvent {
	object SignOut : MainEvent()
	object SeeAllExercises : MainEvent()
	object BodyDetails : MainEvent()
	data class NavigateToExercise(val type: TrainingType) : MainEvent()
}