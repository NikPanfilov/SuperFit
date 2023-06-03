package com.nikpanfilov.superfit.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikpanfilov.network.utils.CoroutineNetworkExceptionHandler
import com.nikpanfilov.shared.body.domain.usecase.GetLastParamsUseCase
import com.nikpanfilov.shared.trainings.TrainingType
import com.nikpanfilov.shared.trainings.domain.usecase.GetTrainingsUseCase
import com.nikpanfilov.superfit.main.domain.usecase.SignOutUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
	private val router: MainRouter,
	private val signOutUseCase: SignOutUseCase,
	private val getLastBodyParamsUseCase: GetLastParamsUseCase,
	private val getTrainingsUseCase: GetTrainingsUseCase
) : ViewModel() {

	val uiState = MutableStateFlow<MainState>(MainState.Initial)

	private val sendErrorHandler = CoroutineNetworkExceptionHandler {}

	init {
		viewModelScope.launch(sendErrorHandler) {
			val bodyParams = getLastBodyParamsUseCase()
			val lastTrainings = getTrainingsUseCase()

			uiState.value = MainState.Content(
				body = bodyParams,
				trainings = lastTrainings
			)
		}
	}

	fun handle(event: MainEvent) {
		when (event) {
			MainEvent.SignOut               -> signOut()
			MainEvent.BodyDetails           -> navigateToMyBody()
			MainEvent.SeeAllExercises       -> navigateToExercises()
			is MainEvent.NavigateToExercise -> navigateToExercise(event.type)
		}
	}

	private fun signOut() {
		signOutUseCase()
		router.navigateToLogin()
	}

	private fun navigateToMyBody() {
		router.navigateToMyBody()
	}

	private fun navigateToExercises() {
		router.navigateToExercises()
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
}