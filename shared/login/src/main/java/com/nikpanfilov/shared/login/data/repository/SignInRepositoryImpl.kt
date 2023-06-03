package com.nikpanfilov.shared.login.data.repository

import com.nikpanfilov.shared.login.data.datasource.SignInDataSource
import com.nikpanfilov.shared.login.domain.entity.AuthCredential
import com.nikpanfilov.shared.login.domain.repository.SignInRepository

class SignInRepositoryImpl(
	private val dataSource: SignInDataSource
) : SignInRepository {

	override suspend fun signIn(credential: AuthCredential) = dataSource.signIn(credential)
}