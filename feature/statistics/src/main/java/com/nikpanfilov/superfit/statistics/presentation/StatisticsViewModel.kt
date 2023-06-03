package com.nikpanfilov.superfit.statistics.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikpanfilov.network.utils.CoroutineNetworkExceptionHandler
import com.nikpanfilov.shared.body.domain.usecase.GetParamsUseCase
import com.nikpanfilov.shared.trainings.domain.usecase.GetTrainingsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class StatisticsViewModel(
	private val router: StatisticsRouter,
	private val getParamsUseCase: GetParamsUseCase,
	private val getTrainingsUseCase: GetTrainingsUseCase
) : ViewModel() {

	val uiState = MutableStateFlow<StatisticsState>(StatisticsState.Initial)

	private val sendErrorHandler = CoroutineNetworkExceptionHandler {}

	init {
		viewModelScope.launch(sendErrorHandler) {
			val params = getParamsUseCase()
			val trainings = getTrainingsUseCase()

			uiState.value = StatisticsState.Content(
				body = params,
				trainings = trainings
			)
		}
	}

	fun handle(event: StatisticsEvent) {
		when (event) {
			StatisticsEvent.NavigateBack -> router.navigateBack()
		}
	}
}