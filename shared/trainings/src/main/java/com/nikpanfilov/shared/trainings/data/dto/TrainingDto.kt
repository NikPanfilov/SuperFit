package com.nikpanfilov.shared.trainings.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TrainingDto(
	@Json(name = "exercise") val exercise: String,
	@Json(name = "repeatCount") val repeatCount: Int,
	@Json(name = "date") val date: String
)