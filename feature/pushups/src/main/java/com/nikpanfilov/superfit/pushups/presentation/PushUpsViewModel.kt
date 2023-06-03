package com.nikpanfilov.superfit.pushups.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikpanfilov.network.utils.CoroutineNetworkExceptionHandler
import com.nikpanfilov.shared.trainings.INCREASE_PUSHUPS
import com.nikpanfilov.shared.trainings.TrainingType
import com.nikpanfilov.shared.trainings.domain.entity.Training
import com.nikpanfilov.shared.trainings.domain.usecase.GetPushUpsCountUseCase
import com.nikpanfilov.shared.trainings.domain.usecase.SavePushUpsCountUseCase
import com.nikpanfilov.shared.trainings.domain.usecase.SaveTrainingUseCase
import com.nikpanfilov.superfit.extensions.getCurrentDate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PushUpsViewModel(
	private val router: PushUpsRouter,
	private val saveTrainingUseCase: SaveTrainingUseCase,
	private val getPushUpsCountUseCase: GetPushUpsCountUseCase,
	private val savePushUpsCountUseCase: SavePushUpsCountUseCase,
) : ViewModel() {

	val uiState = MutableStateFlow<PushUpsState>(PushUpsState.Initial)
	private var current: Int = 0

	private val sendErrorHandler = CoroutineNetworkExceptionHandler {}

	init {
		current = getPushUpsCountUseCase() + INCREASE_PUSHUPS
		uiState.value = PushUpsState.Content(current)
	}

	fun handle(event: PushUpsEvent) {
		when (event) {
			PushUpsEvent.Finish -> finishTraining()
			PushUpsEvent.Done   -> changeLeftCount()
		}
	}

	private fun finishTraining() {
		val left = (uiState.value as? PushUpsState.Content)?.left ?: return
		if (current != left) {
			viewModelScope.launch(sendErrorHandler) {
				saveTrainingUseCase(Training(TrainingType.PUSH_UP, current - left, getCurrentDate()))
				router.navigateToResult(left)
			}
		} else {
			router.navigateBack()
		}
	}

	private fun changeLeftCount() {
		val state = uiState.value as? PushUpsState.Content ?: return
		uiState.value = PushUpsState.Content(left = state.left - 1)
		if (state.left - 1 == 0) {
			savePushUpsCountUseCase(current)
			finishTraining()
		}
	}
}