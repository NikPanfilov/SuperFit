package com.nikpanfilov.shared.login.domain.repository

import com.nikpanfilov.shared.login.domain.entity.AuthCredential
import com.nikpanfilov.shared.login.domain.entity.AuthResponse

interface SignInRepository {

	suspend fun signIn(credential: AuthCredential): AuthResponse
}