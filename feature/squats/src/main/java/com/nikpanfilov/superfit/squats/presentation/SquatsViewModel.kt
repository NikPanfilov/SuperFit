package com.nikpanfilov.superfit.squats.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikpanfilov.network.utils.CoroutineNetworkExceptionHandler
import com.nikpanfilov.shared.trainings.INCREASE_SQUATS
import com.nikpanfilov.shared.trainings.TrainingType
import com.nikpanfilov.shared.trainings.domain.entity.Training
import com.nikpanfilov.shared.trainings.domain.usecase.GetSquatsCountUseCase
import com.nikpanfilov.shared.trainings.domain.usecase.SaveSquatsCountUseCase
import com.nikpanfilov.shared.trainings.domain.usecase.SaveTrainingUseCase
import com.nikpanfilov.superfit.extensions.getCurrentDate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SquatsViewModel(
	private val router: SquatsRouter,
	private val saveTrainingUseCase: SaveTrainingUseCase,
	private val getMaxSquatsUseCase: GetSquatsCountUseCase,
	private val saveMaxSquatsUseCase: SaveSquatsCountUseCase,
) : ViewModel() {

	val uiState = MutableStateFlow<SquatsState>(SquatsState.Initial)
	private var current: Int = 0

	private val sendErrorHandler = CoroutineNetworkExceptionHandler {}

	init {
		current = getMaxSquatsUseCase() + INCREASE_SQUATS
		uiState.value = SquatsState.Content(current)
	}

	fun handle(event: SquatsEvent) {
		when (event) {
			SquatsEvent.NavigateBack -> finishTraining()
			SquatsEvent.Done         -> changeLeftCount()
		}
	}

	private fun finishTraining() {
		val left = (uiState.value as? SquatsState.Content)?.left ?: return
		if (current != left) {
			viewModelScope.launch(sendErrorHandler) {
				saveTrainingUseCase(Training(TrainingType.SQUATS, current - left, getCurrentDate()))
				router.navigateToResult(left)
			}
		} else {
			router.navigateBack()
		}
	}

	private fun changeLeftCount() {
		val state = uiState.value as? SquatsState.Content ?: return
		uiState.value = SquatsState.Content(left = state.left - 1)
		Log.i("squats", (state.left - 1).toString())
		if ((uiState.value as SquatsState.Content).left == 0)
			viewModelScope.launch(sendErrorHandler) {
				saveTrainingUseCase(Training(TrainingType.SQUATS, current, getCurrentDate()))
				saveMaxSquatsUseCase(current)
				router.navigateToResult(0)
			}
	}
}