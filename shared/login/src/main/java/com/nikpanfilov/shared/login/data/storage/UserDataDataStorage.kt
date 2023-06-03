package com.nikpanfilov.shared.login.data.storage

import com.nikpanfilov.shared.login.domain.entity.UserData

interface UserDataDataStorage {

	fun saveData(data: UserData)
}