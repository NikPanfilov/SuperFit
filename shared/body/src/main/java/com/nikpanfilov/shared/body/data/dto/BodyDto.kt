package com.nikpanfilov.shared.body.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BodyDto(
	@Json(name = "weight") val weight: Int,
	@Json(name = "height") val height: Int,
	@Json(name = "date") val date: String
)