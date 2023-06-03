package com.nikpanfilov.shared.trainings.data.api

import com.nikpanfilov.shared.trainings.data.dto.TrainingDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TrainingsHistoryApi {

	@GET("api/trainings")
	suspend fun getTrainings(): List<TrainingDto>

	@POST("api/trainings")
	suspend fun saveTraining(@Body training: TrainingDto)
}