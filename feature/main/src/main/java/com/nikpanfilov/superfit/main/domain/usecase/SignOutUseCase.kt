package com.nikpanfilov.superfit.main.domain.usecase

import com.nikpanfilov.superfit.main.domain.repository.SignOutRepository

class SignOutUseCase(private val repository: SignOutRepository) {

	operator fun invoke() {
		repository.signOut()
	}
}