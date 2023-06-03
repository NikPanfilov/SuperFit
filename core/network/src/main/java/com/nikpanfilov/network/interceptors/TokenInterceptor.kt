package com.nikpanfilov.network.interceptors

import com.nikpanfilov.network.token.domain.usecase.LoadTokenUseCase
import okhttp3.Interceptor

fun tokenInterceptor(loadTokenUseCase: LoadTokenUseCase): Interceptor {
	return Interceptor { chain ->

		val request = chain.request()
			.newBuilder()
			.addHeader("Authorization", "Bearer ${loadTokenUseCase().accessToken}")
			.build()
		chain.proceed(request)
	}
}