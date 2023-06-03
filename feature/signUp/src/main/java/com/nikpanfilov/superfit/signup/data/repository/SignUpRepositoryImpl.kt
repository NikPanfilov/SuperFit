package com.nikpanfilov.superfit.signup.data.repository

import com.nikpanfilov.superfit.signup.data.datasource.SignUpDataSource
import com.nikpanfilov.superfit.signup.domain.entity.RegistrationBody
import com.nikpanfilov.superfit.signup.domain.repository.SignUpRepository

class SignUpRepositoryImpl(private val dataSource: SignUpDataSource) : SignUpRepository {

	override suspend fun signUp(data: RegistrationBody) {
		dataSource.signUp(data)
	}
}