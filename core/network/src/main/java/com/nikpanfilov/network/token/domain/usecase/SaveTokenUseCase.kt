package com.nikpanfilov.network.token.domain.usecase

import com.nikpanfilov.network.token.domain.model.AuthTokenPair
import com.nikpanfilov.network.token.domain.repository.TokenRepository

class SaveTokenUseCase(
	private val repository: TokenRepository,
) {

	operator fun invoke(authTokenPair: AuthTokenPair) = repository.save(authTokenPair)
}