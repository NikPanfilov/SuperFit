package com.nikpanfilov.shared.login.data.repository

import com.nikpanfilov.shared.login.data.storage.UserDataDataStorage
import com.nikpanfilov.shared.login.domain.entity.UserData
import com.nikpanfilov.shared.login.domain.repository.UserDataRepository

class UserDataRepositoryImpl(private val dataStorage: UserDataDataStorage) : UserDataRepository {

	override fun saveData(data: UserData) {
		dataStorage.saveData(data)
	}
}