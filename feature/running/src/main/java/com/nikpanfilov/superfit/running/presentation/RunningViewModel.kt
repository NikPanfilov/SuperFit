package com.nikpanfilov.superfit.running.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikpanfilov.network.utils.CoroutineNetworkExceptionHandler
import com.nikpanfilov.shared.trainings.INCREASE_RUNNING
import com.nikpanfilov.shared.trainings.TrainingType
import com.nikpanfilov.shared.trainings.domain.entity.Training
import com.nikpanfilov.shared.trainings.domain.usecase.GetRunningCountUseCase
import com.nikpanfilov.shared.trainings.domain.usecase.SaveRunningCountUseCase
import com.nikpanfilov.shared.trainings.domain.usecase.SaveTrainingUseCase
import com.nikpanfilov.superfit.extensions.getCurrentDate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RunningViewModel(
	private val router: RunningRouter,
	private val saveTrainingUseCase: SaveTrainingUseCase,
	private val getRunningCountUseCase: GetRunningCountUseCase,
	private val saveRunningCountUseCase: SaveRunningCountUseCase,
) : ViewModel() {

	val uiState = MutableStateFlow<RunningState>(RunningState.Initial)
	private var current: Int = 0

	private val sendErrorHandler = CoroutineNetworkExceptionHandler {}

	init {
		current = getRunningCountUseCase() + INCREASE_RUNNING
		uiState.value = RunningState.Content(current)
	}

	fun handle(event: RunningEvent) {
		when (event) {
			RunningEvent.Finish             -> finishTraining()
			is RunningEvent.ChangeLeftCount -> changeLeftCount(event.done)
		}
	}

	private fun finishTraining() {
		val left = (uiState.value as? RunningState.Content)?.left ?: return
		if (current != left) {
			viewModelScope.launch(sendErrorHandler) {
				saveTrainingUseCase(Training(TrainingType.RUNNING, current - left, getCurrentDate()))
				if (left == 0)
					saveRunningCountUseCase(current)
				router.navigateToResult(left)
			}
		} else {
			router.navigateBack()
		}
	}

	private fun changeLeftCount(done: Int) {
		val state = uiState.value as? RunningState.Content ?: return
		uiState.value = RunningState.Content(left = state.left - done)
	}
}