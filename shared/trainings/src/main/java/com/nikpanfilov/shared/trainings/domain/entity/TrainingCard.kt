package com.nikpanfilov.shared.trainings.domain.entity

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class TrainingCard(
	@StringRes val title: Int,
	@StringRes val text: Int,
	@DrawableRes val image: Int
)
