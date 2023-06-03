package com.nikpanfilov.shared.body.data.api

import com.nikpanfilov.shared.body.data.dto.BodyDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface BodyParamsHistoryApi {

	@GET("api/profile/params/history")
	suspend fun getParams(): List<BodyDto>

	@POST("api/profile/params")
	suspend fun setParams(@Body params: BodyDto)
}