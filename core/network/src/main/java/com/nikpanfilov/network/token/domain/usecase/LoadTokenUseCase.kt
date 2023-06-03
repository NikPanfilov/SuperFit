package com.nikpanfilov.network.token.domain.usecase

import com.nikpanfilov.network.token.domain.repository.TokenRepository

class LoadTokenUseCase(
	private val repository: TokenRepository,
) {

	operator fun invoke() = repository.load()
}