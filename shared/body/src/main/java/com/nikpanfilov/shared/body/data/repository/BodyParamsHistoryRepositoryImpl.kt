package com.nikpanfilov.shared.body.data.repository

import com.nikpanfilov.shared.body.data.api.BodyParamsHistoryApi
import com.nikpanfilov.shared.body.data.mapper.toDto
import com.nikpanfilov.shared.body.data.mapper.toEntity
import com.nikpanfilov.shared.body.domain.entity.Body
import com.nikpanfilov.shared.body.domain.repository.BodyParamsHistoryRepository

class BodyParamsHistoryRepositoryImpl(private val api: BodyParamsHistoryApi) : BodyParamsHistoryRepository {

	override suspend fun getParams(): List<Body> = api.getParams().map { it.toEntity() }

	override suspend fun setParams(params: Body) {
		api.setParams(params.toDto())
	}
}