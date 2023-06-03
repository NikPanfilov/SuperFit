package com.nikpanfilov.network.token.data.repository

import com.nikpanfilov.network.token.data.storage.TokenDataStorage
import com.nikpanfilov.network.token.domain.model.AuthTokenPair
import com.nikpanfilov.network.token.domain.repository.TokenRepository

class TokenRepositoryImpl(
	private val storage: TokenDataStorage
) : TokenRepository {

	override fun save(authTokenPair: AuthTokenPair) = storage.save(authTokenPair)

	override fun load() = storage.load()
}