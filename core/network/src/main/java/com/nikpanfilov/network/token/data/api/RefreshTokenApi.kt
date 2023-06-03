package com.nikpanfilov.network.token.data.api

import com.nikpanfilov.shared.login.data.dto.AccessTokenDto
import com.nikpanfilov.shared.login.data.dto.RefreshTokenDto
import retrofit2.http.Body
import retrofit2.http.POST

interface RefreshTokenApi {

	@POST("api/auth/token/refresh")
	suspend fun refresh(@Body token: RefreshTokenDto): AccessTokenDto
}