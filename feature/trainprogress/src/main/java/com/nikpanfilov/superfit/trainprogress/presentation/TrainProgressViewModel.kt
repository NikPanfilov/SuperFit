package com.nikpanfilov.superfit.trainprogress.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikpanfilov.network.utils.CoroutineNetworkExceptionHandler
import com.nikpanfilov.shared.trainings.TrainingType
import com.nikpanfilov.shared.trainings.domain.usecase.GetTrainingsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TrainProgressViewModel(private val router: TrainProgressRouter, private val getTrainingsUseCase: GetTrainingsUseCase) : ViewModel() {

	val uiState = MutableStateFlow<TrainProgressState>(TrainProgressState.Initial)
	private val sendErrorHandler = CoroutineNetworkExceptionHandler {}

	init {
		viewModelScope.launch(sendErrorHandler) {
			val trainings = getTrainingsUseCase()

			val progress = TrainingType.values().associateWith { trainingType ->
				val train = trainings.filter { it.exercise == trainingType }.sortedByDescending { it.date }
				val lastTraining = train.firstOrNull()
				val lastRepeatCount = lastTraining?.repeatCount ?: 0
				val progress = if (train.size > 1) progress(train[0].repeatCount, train[1].repeatCount) else 0

				Progress(lastRepeatCount, progress)
			}

			uiState.value = TrainProgressState.Content(progress)
		}
	}

	private fun progress(first: Int, second: Int): Int = ((first - second) / second) * 100

	fun handle(event: TrainProgressEvent) {
		when (event) {
			TrainProgressEvent.NavigateBack -> router.navigateBack()
		}
	}
}