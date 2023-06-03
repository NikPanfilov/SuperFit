package com.nikpanfilov.network.token.data.datasource

import com.nikpanfilov.network.token.domain.model.AccessToken
import com.nikpanfilov.network.token.domain.model.RefreshToken

interface RefreshTokenDataSource {

	suspend fun refresh(token: RefreshToken): AccessToken
}