package com.nikpanfilov.superfit.crunch.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikpanfilov.network.utils.CoroutineNetworkExceptionHandler
import com.nikpanfilov.shared.trainings.INCREASE_CRUNCH
import com.nikpanfilov.shared.trainings.MIN_CRUNCH
import com.nikpanfilov.shared.trainings.TrainingType
import com.nikpanfilov.shared.trainings.domain.entity.Training
import com.nikpanfilov.shared.trainings.domain.usecase.GetTrainingsUseCase
import com.nikpanfilov.shared.trainings.domain.usecase.SaveTrainingUseCase
import com.nikpanfilov.superfit.extensions.getCurrentDate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CrunchViewModel(
	private val router: CrunchRouter,
	private val getTrainingsUseCase: GetTrainingsUseCase,
	private val saveTrainingUseCase: SaveTrainingUseCase
) : ViewModel() {

	val uiState = MutableStateFlow<CrunchState>(CrunchState.Initial)
	private var current: Int = 0

	private val sendErrorHandler = CoroutineNetworkExceptionHandler {}

	init {
		viewModelScope.launch(sendErrorHandler) {
			val trainings = getTrainingsUseCase()
			val lastRepeats = trainings.filter { it.exercise == TrainingType.CRUNCH }.maxByOrNull { it.date }?.repeatCount ?: MIN_CRUNCH
			current = lastRepeats + INCREASE_CRUNCH
			uiState.value = CrunchState.Content(current)
		}
	}

	fun handle(event: CrunchEvent) {
		when (event) {
			CrunchEvent.Finish -> finishTraining()
		}
	}

	private fun finishTraining() {
		viewModelScope.launch(sendErrorHandler) {
			saveTrainingUseCase(Training(TrainingType.CRUNCH, current, getCurrentDate()))
			router.navigateToResult(0)
		}
	}
}