package com.nikpanfilov.shared.login.domain.usecase

import com.nikpanfilov.shared.login.domain.entity.AuthCredential
import com.nikpanfilov.shared.login.domain.entity.AuthResponse
import com.nikpanfilov.shared.login.domain.repository.SignInRepository

class SignInUseCase(private val repository: SignInRepository) {

	suspend operator fun invoke(credential: AuthCredential): AuthResponse = repository.signIn(credential)
}