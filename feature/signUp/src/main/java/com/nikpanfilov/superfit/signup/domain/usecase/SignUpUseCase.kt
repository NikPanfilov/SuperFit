package com.nikpanfilov.superfit.signup.domain.usecase

import com.nikpanfilov.superfit.signup.domain.entity.RegistrationBody
import com.nikpanfilov.superfit.signup.domain.repository.SignUpRepository

class SignUpUseCase(private val repository: SignUpRepository) {

	suspend operator fun invoke(data: RegistrationBody) {
		repository.signUp(data)
	}
}