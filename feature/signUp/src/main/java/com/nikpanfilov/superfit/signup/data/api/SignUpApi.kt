package com.nikpanfilov.superfit.signup.data.api

import com.nikpanfilov.superfit.signup.data.dto.RegistrationBodyDto
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpApi {

	@POST("api/auth/register")
	suspend fun signUp(@Body data: RegistrationBodyDto)
}