package com.nikpanfilov.superfit.exercises.presentation

import androidx.lifecycle.ViewModel
import com.nikpanfilov.shared.trainings.TrainingType

class ExercisesViewModel(private val router: ExercisesRouter) : ViewModel() {

	fun handle(event: ExercisesEvent) {
		when (event) {
			is ExercisesEvent.NavigateToExercise -> navigateToExercise(event.type)
			ExercisesEvent.NavigateBack          -> navigateBack()
		}
	}

	private fun navigateToExercise(type: TrainingType) {
		when (type) {
			TrainingType.CRUNCH  -> router.navigateToCrunch()
			TrainingType.SQUATS  -> router.navigateToSquats()
			TrainingType.PUSH_UP -> router.navigateToPushUps()
			TrainingType.PLANK   -> router.navigateToPlank()
			TrainingType.RUNNING -> router.navigateToRunning()
		}
	}

	private fun navigateBack() {
		router.navigateBack()
	}
}