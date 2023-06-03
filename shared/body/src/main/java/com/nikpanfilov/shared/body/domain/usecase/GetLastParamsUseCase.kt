package com.nikpanfilov.shared.body.domain.usecase

import com.nikpanfilov.shared.body.domain.entity.Body
import com.nikpanfilov.shared.body.domain.repository.BodyParamsHistoryRepository

class GetLastParamsUseCase(private val repository: BodyParamsHistoryRepository) {

	suspend operator fun invoke(): Body? = repository.getParams().maxByOrNull { it.date }
}