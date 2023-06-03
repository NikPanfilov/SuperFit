package com.nikpanfilov.shared.body.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProfilePhotoDto(
	@Json(name = "id") val id: String,
	@Json(name = "uploaded") val uploaded: Long
)
