package com.nikpanfilov.shared.login.domain.repository

import com.nikpanfilov.shared.login.domain.entity.UserData

interface UserDataRepository {

	fun saveData(data: UserData)
}