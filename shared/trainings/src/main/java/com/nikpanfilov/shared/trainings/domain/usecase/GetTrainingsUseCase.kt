package com.nikpanfilov.shared.trainings.domain.usecase

import com.nikpanfilov.shared.trainings.domain.entity.Training
import com.nikpanfilov.shared.trainings.domain.repository.TrainingsHistoryRepository

class GetTrainingsUseCase(private val repository: TrainingsHistoryRepository) {

	suspend operator fun invoke(): List<Training> = repository.getTrainings()
}