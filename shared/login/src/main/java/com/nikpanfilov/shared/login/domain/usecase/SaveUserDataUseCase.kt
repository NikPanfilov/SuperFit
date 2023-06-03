package com.nikpanfilov.shared.login.domain.usecase

import com.nikpanfilov.shared.login.domain.entity.UserData
import com.nikpanfilov.shared.login.domain.repository.UserDataRepository

class SaveUserDataUseCase(private val repository: UserDataRepository) {

	operator fun invoke(data: UserData) {
		repository.saveData(data)
	}
}