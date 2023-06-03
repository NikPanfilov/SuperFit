package com.nikpanfilov.network.token.domain.repository

import com.nikpanfilov.network.token.domain.model.AccessToken
import com.nikpanfilov.network.token.domain.model.RefreshToken

interface RefreshTokenRepository {

	suspend fun refresh(token: RefreshToken): AccessToken
}