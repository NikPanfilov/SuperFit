package com.nikpanfilov.shared.trainings.domain.entity

import com.nikpanfilov.shared.trainings.TrainingType

data class Training(
	val exercise: TrainingType,
	val repeatCount: Int,
	val date: String
)
