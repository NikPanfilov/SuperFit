package com.nikpanfilov.shared.login.data.mapper

import com.nikpanfilov.shared.login.data.dto.AccessTokenDto
import com.nikpanfilov.shared.login.data.dto.RefreshTokenDto

internal fun AccessTokenDto.toEntity() = com.nikpanfilov.network.token.domain.model.AccessToken(accessToken = accessToken, expired = expired)

internal fun com.nikpanfilov.network.token.domain.model.RefreshToken.toDto() = RefreshTokenDto(refreshToken = refreshToken)