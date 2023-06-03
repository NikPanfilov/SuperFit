package com.nikpanfilov.shared.trainings.domain.usecase

import com.nikpanfilov.shared.trainings.domain.repository.TrainingCountRepository

class GetCrunchCountUseCase(private val repository: TrainingCountRepository) {

	operator fun invoke(): Int = repository.getMaxCrunch()
}