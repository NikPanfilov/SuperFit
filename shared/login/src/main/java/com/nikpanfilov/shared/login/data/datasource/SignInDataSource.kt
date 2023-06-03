package com.nikpanfilov.shared.login.data.datasource

import com.nikpanfilov.shared.login.domain.entity.AuthCredential
import com.nikpanfilov.shared.login.domain.entity.AuthResponse

interface SignInDataSource {

	suspend fun signIn(credential: AuthCredential): AuthResponse
}