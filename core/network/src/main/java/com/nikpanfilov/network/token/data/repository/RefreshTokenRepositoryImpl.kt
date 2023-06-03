package com.nikpanfilov.network.token.data.repository

import com.nikpanfilov.network.token.data.datasource.RefreshTokenDataSource
import com.nikpanfilov.network.token.domain.model.AccessToken
import com.nikpanfilov.network.token.domain.model.RefreshToken
import com.nikpanfilov.network.token.domain.repository.RefreshTokenRepository

class RefreshTokenRepositoryImpl(private val dataSource: RefreshTokenDataSource) : RefreshTokenRepository {

	override suspend fun refresh(token: RefreshToken): AccessToken = dataSource.refresh(token)
}