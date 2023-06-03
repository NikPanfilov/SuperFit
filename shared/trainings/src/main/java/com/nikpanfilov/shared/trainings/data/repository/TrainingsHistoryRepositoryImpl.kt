package com.nikpanfilov.shared.trainings.data.repository

import com.nikpanfilov.shared.trainings.data.api.TrainingsHistoryApi
import com.nikpanfilov.shared.trainings.data.mapper.toDto
import com.nikpanfilov.shared.trainings.data.mapper.toEntity
import com.nikpanfilov.shared.trainings.domain.entity.Training
import com.nikpanfilov.shared.trainings.domain.repository.TrainingsHistoryRepository

class TrainingsHistoryRepositoryImpl(private val api: TrainingsHistoryApi) : TrainingsHistoryRepository {

	override suspend fun getTrainings(): List<Training> = api.getTrainings().map { it.toEntity() }
	override suspend fun saveTraining(training: Training) {
		api.saveTraining(training.toDto())
	}
}