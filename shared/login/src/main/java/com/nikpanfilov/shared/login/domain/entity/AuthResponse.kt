package com.nikpanfilov.shared.login.domain.entity

data class AuthResponse(
	val username: String,
	val refreshToken: String,
	val expired: Long
)