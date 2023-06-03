package com.nikpanfilov.shared.trainings.domain.usecase

import com.nikpanfilov.shared.trainings.domain.repository.TrainingCountRepository

class SavePlankCountUseCase(private val repository: TrainingCountRepository) {

	operator fun invoke(count: Int) = repository.setMaxPlank(count)
}