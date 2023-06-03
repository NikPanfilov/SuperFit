package com.nikpanfilov.superfit.lab2.data.repository

import com.nikpanfilov.superfit.lab2.data.storage.FirstStartDataStorage
import com.nikpanfilov.superfit.lab2.domain.repository.FirstStartRepository

class FirstStartRepositoryImpl(private val dataStorage: FirstStartDataStorage) : FirstStartRepository {

	override fun isFirstStart(): Boolean = dataStorage.isFirstStart()

}