package com.nikpanfilov.network.token.domain.usecase

import com.nikpanfilov.network.token.domain.model.AccessToken
import com.nikpanfilov.network.token.domain.model.RefreshToken
import com.nikpanfilov.network.token.domain.repository.RefreshTokenRepository

class RefreshTokenUseCase(private val repository: RefreshTokenRepository) {

	suspend operator fun invoke(token: String): AccessToken = repository.refresh(RefreshToken(token))
}