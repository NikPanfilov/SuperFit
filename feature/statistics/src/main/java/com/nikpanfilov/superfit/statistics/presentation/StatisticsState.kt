package com.nikpanfilov.superfit.statistics.presentation

import com.nikpanfilov.shared.body.domain.entity.Body
import com.nikpanfilov.shared.trainings.domain.entity.Training

sealed class StatisticsState {
	object Initial : StatisticsState()

	data class Content(
		val body: List<Body>,
		val trainings: List<Training>
	) : StatisticsState()
}