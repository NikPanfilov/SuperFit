package com.nikpanfilov.shared.trainings.domain.repository

import com.nikpanfilov.shared.trainings.domain.entity.Training

interface TrainingsHistoryRepository {

	suspend fun getTrainings(): List<Training>

	suspend fun saveTraining(training: Training)
}