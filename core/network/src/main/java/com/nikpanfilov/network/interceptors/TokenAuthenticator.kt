package com.nikpanfilov.network.interceptors

import com.nikpanfilov.network.token.domain.model.AuthTokenPair
import com.nikpanfilov.network.token.domain.usecase.LoadTokenUseCase
import com.nikpanfilov.network.token.domain.usecase.RefreshTokenUseCase
import com.nikpanfilov.network.token.domain.usecase.SaveTokenUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import kotlin.coroutines.CoroutineContext

class TokenAuthenticator(
	private val getTokenUseCase: LoadTokenUseCase,
	private val saveTokenUseCase: SaveTokenUseCase,
	private val refreshTokensUseCase: RefreshTokenUseCase
) : Authenticator, CoroutineScope {

	// Автоматически вызывается, когда сервер возвращает код ответа 401
	override fun authenticate(route: Route?, response: Response): Request? {
		// Обновляем токен при первой попытке
		if (responseCount(response) <= 3) {
			val tokenPair = getTokenUseCase()
			val newToken = runBlocking { refreshTokensUseCase(tokenPair.refreshToken) }
			saveTokenUseCase(AuthTokenPair(accessToken = newToken.accessToken, refreshToken = tokenPair.refreshToken))

			return response.request
				.newBuilder()
				.header("Authorization", "Bearer ${newToken.accessToken}")
				.build()
		}
		return null
	}

	// Считает количество попыток обработки ответа 401
	private fun responseCount(response: Response): Int {
		var count = 1
		while (response.priorResponse != null) {
			count++
		}
		return count
	}

	private val job = Job()

	override val coroutineContext: CoroutineContext
		get() = Dispatchers.IO + job
}