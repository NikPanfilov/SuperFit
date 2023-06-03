package com.nikpanfilov.network.token.domain.repository

import com.nikpanfilov.network.token.domain.model.AuthTokenPair

interface TokenRepository {

	fun save(authTokenPair: AuthTokenPair)

	fun load(): AuthTokenPair
}