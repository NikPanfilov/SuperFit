package com.nikpanfilov.network.token.data.storage

import com.nikpanfilov.network.token.domain.model.AuthTokenPair

interface TokenDataStorage {

	fun save(authTokenPair: AuthTokenPair)

	fun load(): AuthTokenPair
}