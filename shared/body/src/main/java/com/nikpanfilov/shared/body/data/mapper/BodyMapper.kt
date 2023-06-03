package com.nikpanfilov.shared.body.data.mapper

import com.nikpanfilov.shared.body.data.dto.BodyDto
import com.nikpanfilov.shared.body.domain.entity.Body

internal fun BodyDto.toEntity() = Body(weight = weight, height = height, date = date)

internal fun Body.toDto() = BodyDto(weight = weight, height = height, date = date)