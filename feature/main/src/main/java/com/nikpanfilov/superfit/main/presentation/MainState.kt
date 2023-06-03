package com.nikpanfilov.superfit.main.presentation

import com.nikpanfilov.shared.body.domain.entity.Body
import com.nikpanfilov.shared.trainings.domain.entity.Training

sealed class MainState {
	object Initial : MainState()

	data class Content(
		val body: Body?,
		val trainings: List<Training>
	) : MainState()
}
