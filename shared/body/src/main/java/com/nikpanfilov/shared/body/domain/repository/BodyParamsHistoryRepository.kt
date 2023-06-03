package com.nikpanfilov.shared.body.domain.repository

import com.nikpanfilov.shared.body.domain.entity.Body

interface BodyParamsHistoryRepository {

	suspend fun getParams(): List<Body>

	suspend fun setParams(params: Body)
}