package com.nikpanfilov.shared.body.domain.usecase

import com.nikpanfilov.shared.body.domain.entity.Body
import com.nikpanfilov.shared.body.domain.repository.BodyParamsHistoryRepository

class GetParamsUseCase(private val repository: BodyParamsHistoryRepository) {

	suspend operator fun invoke(): List<Body> = repository.getParams()
}