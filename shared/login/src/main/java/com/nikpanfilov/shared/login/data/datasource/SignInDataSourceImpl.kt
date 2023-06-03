package com.nikpanfilov.shared.login.data.datasource

import com.nikpanfilov.shared.login.data.api.SignInApi
import com.nikpanfilov.shared.login.data.mapper.toEntity
import com.nikpanfilov.shared.login.domain.entity.AuthCredential
import com.nikpanfilov.signin.data.mapper.toDto

class SignInDataSourceImpl(private val api: SignInApi) : SignInDataSource {

	override suspend fun signIn(credential: AuthCredential) = api.signIn(credential.toDto()).toEntity()
}