package com.nikpanfilov.shared.login.data.api

import com.nikpanfilov.shared.login.data.dto.AuthCredentialDto
import com.nikpanfilov.shared.login.data.dto.AuthResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface SignInApi {

	@POST("api/auth/token")
	suspend fun signIn(@Body data: AuthCredentialDto): AuthResponseDto
}