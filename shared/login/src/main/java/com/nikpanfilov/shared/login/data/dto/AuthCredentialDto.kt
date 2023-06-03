package com.nikpanfilov.shared.login.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthCredentialDto(
	@Json(name = "login") val email: String,
	@Json(name = "password") val password: String
)
