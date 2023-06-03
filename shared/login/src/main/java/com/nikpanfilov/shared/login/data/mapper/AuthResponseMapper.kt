package com.nikpanfilov.shared.login.data.mapper

import com.nikpanfilov.shared.login.data.dto.AuthResponseDto
import com.nikpanfilov.shared.login.domain.entity.AuthResponse

internal fun AuthResponseDto.toEntity() = AuthResponse(username = username, refreshToken = refreshToken, expired = expired)