package com.nikpanfilov.shared.trainings.domain.usecase

import com.nikpanfilov.shared.trainings.domain.entity.Training
import com.nikpanfilov.shared.trainings.domain.repository.TrainingsHistoryRepository

class SaveTrainingUseCase(private val repository: TrainingsHistoryRepository) {

	suspend operator fun invoke(training: Training) {
		repository.saveTraining(training)
	}
}