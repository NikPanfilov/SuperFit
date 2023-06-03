package com.nikpanfilov.shared.login.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AccessTokenDto(
	@Json(name = "access_token") val accessToken: String,
	@Json(name = "expired") val expired: Long
)

