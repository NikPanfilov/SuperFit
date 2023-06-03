package com.nikpanfilov.superfit.signup.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegistrationBodyDto(
	@Json(name = "login") val login: String,
	@Json(name = "password") val password: String
)
