package com.nikpanfilov.shared.trainings.domain.usecase

import com.nikpanfilov.shared.trainings.domain.repository.TrainingCountRepository

class GetPushUpsCountUseCase(private val repository: TrainingCountRepository) {

	operator fun invoke(): Int = repository.getMaxPushUps()
}