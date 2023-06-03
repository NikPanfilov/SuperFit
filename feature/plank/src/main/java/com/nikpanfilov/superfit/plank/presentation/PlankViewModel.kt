package com.nikpanfilov.superfit.plank.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikpanfilov.network.utils.CoroutineNetworkExceptionHandler
import com.nikpanfilov.shared.trainings.INCREASE_PLANK
import com.nikpanfilov.shared.trainings.TrainingType
import com.nikpanfilov.shared.trainings.domain.entity.Training
import com.nikpanfilov.shared.trainings.domain.usecase.GetPlankCountUseCase
import com.nikpanfilov.shared.trainings.domain.usecase.SavePlankCountUseCase
import com.nikpanfilov.shared.trainings.domain.usecase.SaveTrainingUseCase
import com.nikpanfilov.superfit.extensions.getCurrentDate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PlankViewModel(
	private val router: PlankRouter,
	private val saveTrainingUseCase: SaveTrainingUseCase,
	private val getPlankCountUseCase: GetPlankCountUseCase,
	private val savePlankCountUseCase: SavePlankCountUseCase,
) : ViewModel() {

	val uiState = MutableStateFlow<PlankState>(PlankState.Initial)
	private var current = 0

	private val sendErrorHandler = CoroutineNetworkExceptionHandler {}

	init {
		current = getPlankCountUseCase() + INCREASE_PLANK
		uiState.value = PlankState.Content(current)
	}

	fun handle(event: PlankEvent) {
		when (event) {
			PlankEvent.Finish             -> finishTraining()
			is PlankEvent.ChangeLeftCount -> changeLeftCount(event.left)
		}
	}

	private fun finishTraining() {
		val left = (uiState.value as? PlankState.Content)?.left ?: return
		if (current != left) {
			viewModelScope.launch(sendErrorHandler) {
				saveTrainingUseCase(Training(TrainingType.PLANK, current - left, getCurrentDate()))
				if (left == 0)
					savePlankCountUseCase(current)
				router.navigateToResult(left)
			}
		} else {
			router.navigateBack()
		}
	}

	private fun changeLeftCount(left: Int) {
		uiState.value = PlankState.Content(left = left)
	}
}