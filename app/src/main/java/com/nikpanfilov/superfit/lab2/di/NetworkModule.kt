package com.nikpanfilov.superfit.lab2.di

import com.nikpanfilov.network.interceptors.TokenAuthenticator
import com.nikpanfilov.network.interceptors.loggingInterceptor
import com.nikpanfilov.network.interceptors.noConnectionInterceptor
import com.nikpanfilov.network.interceptors.tokenInterceptor
import com.nikpanfilov.network.provider.provideMoshi
import com.nikpanfilov.network.provider.provideOkHttpClient
import com.nikpanfilov.network.provider.provideRetrofit
import com.nikpanfilov.superfit.lab2.App.Companion.BACKEND
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val LOG_INTERCEPTOR = "logInterceptor"
const val NO_CONNECT_INTERCEPTOR = "noConnectionInterceptor"
const val TOKEN_INTERCEPTOR = "tokenInterceptor"
const val TOKEN_AUTHENTICATOR = "tokenAuthenticator"
const val ORIGINAL = "original"

val networkModule = module {
	single(named(LOG_INTERCEPTOR)) { loggingInterceptor() }
	single(named(NO_CONNECT_INTERCEPTOR)) { noConnectionInterceptor(androidContext()) }
	single(named(TOKEN_INTERCEPTOR)) { tokenInterceptor(get()) }
	single(named(TOKEN_AUTHENTICATOR)) { TokenAuthenticator(getTokenUseCase = get(), saveTokenUseCase = get(), refreshTokensUseCase = get()) }

	single { provideMoshi() }

	single(named(ORIGINAL)) {
		provideOkHttpClient(
			logging = get(named(LOG_INTERCEPTOR)),
			noConnection = get(named(NO_CONNECT_INTERCEPTOR)),
			token = get(named(TOKEN_INTERCEPTOR)),
			tokenAuthenticator = get(named(TOKEN_AUTHENTICATOR))
		)
	}
	single(named(ORIGINAL)) {
		provideRetrofit(
			okHttpClient = get(named(ORIGINAL)),
			moshi = get(),
			url = getProperty(BACKEND)
		)
	}
}