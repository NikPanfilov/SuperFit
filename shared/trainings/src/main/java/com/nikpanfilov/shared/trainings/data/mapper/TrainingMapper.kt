package com.nikpanfilov.shared.trainings.data.mapper

import com.nikpanfilov.shared.trainings.TrainingType
import com.nikpanfilov.shared.trainings.data.dto.TrainingDto
import com.nikpanfilov.shared.trainings.domain.entity.Training

internal fun TrainingDto.toEntity() = Training(exercise = exercise.toTrainingType(), repeatCount = repeatCount, date = date)

internal fun Training.toDto() = TrainingDto(exercise = exercise.type, repeatCount = repeatCount, date = date)

private fun String.toTrainingType() = TrainingType.values().find { it.type == this } ?: TrainingType.CRUNCH