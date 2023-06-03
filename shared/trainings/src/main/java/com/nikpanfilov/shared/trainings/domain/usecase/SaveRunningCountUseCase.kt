package com.nikpanfilov.shared.trainings.domain.usecase

import com.nikpanfilov.shared.trainings.domain.repository.TrainingCountRepository

class SaveRunningCountUseCase(private val repository: TrainingCountRepository) {

	operator fun invoke(count: Int) = repository.setMaxRunning(count)
}