package com.nikpanfilov.shared.login.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthResponseDto(
	@Json(name = "username") val username: String,
	@Json(name = "refresh_token") val refreshToken: String,
	@Json(name = "expired") val expired: Long
)