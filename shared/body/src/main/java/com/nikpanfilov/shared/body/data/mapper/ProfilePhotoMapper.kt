package com.nikpanfilov.shared.body.data.mapper

import com.nikpanfilov.shared.body.data.dto.ProfilePhotoDto
import com.nikpanfilov.shared.body.domain.entity.ProfilePhoto

internal fun ProfilePhotoDto.toEntity() = ProfilePhoto(id = id, uploaded = uploaded)