package com.nikpanfilov.network.token.domain.model

data class AccessToken(
	val accessToken: String,
	val expired: Long
)

