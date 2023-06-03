package com.nikpanfilov.superfit.signup.data.datasource

import com.nikpanfilov.superfit.signup.data.api.SignUpApi
import com.nikpanfilov.superfit.signup.data.mapper.toDto
import com.nikpanfilov.superfit.signup.domain.entity.RegistrationBody

class SignUpDataSourceImpl(private val api: SignUpApi) : SignUpDataSource {

	override suspend fun signUp(data: RegistrationBody) {
		api.signUp(data.toDto())
	}
}