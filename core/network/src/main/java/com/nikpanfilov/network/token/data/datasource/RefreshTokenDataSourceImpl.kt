package com.nikpanfilov.network.token.data.datasource

import com.nikpanfilov.network.token.domain.model.AccessToken
import com.nikpanfilov.network.token.domain.model.RefreshToken
import com.nikpanfilov.shared.login.data.mapper.toDto
import com.nikpanfilov.shared.login.data.mapper.toEntity

class RefreshTokenDataSourceImpl(private val api: com.nikpanfilov.network.token.data.api.RefreshTokenApi) : RefreshTokenDataSource {

	override suspend fun refresh(token: RefreshToken): AccessToken = api.refresh(token.toDto()).toEntity()
}