package com.nikpanfilov.superfit.main.data.repository

import com.nikpanfilov.superfit.main.data.storage.SignOutDataStorage
import com.nikpanfilov.superfit.main.domain.repository.SignOutRepository

class SignOutRepositoryImpl(private val storage: SignOutDataStorage) : SignOutRepository {

	override fun signOut() {
		storage.signOut()
	}
}