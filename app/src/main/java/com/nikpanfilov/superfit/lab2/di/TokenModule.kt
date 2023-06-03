package com.nikpanfilov.superfit.lab2.di

import com.nikpanfilov.network.createRetrofitService
import com.nikpanfilov.network.provider.provideRefreshOkHttpClient
import com.nikpanfilov.network.provider.provideRetrofit
import com.nikpanfilov.network.token.data.api.RefreshTokenApi
import com.nikpanfilov.network.token.data.datasource.RefreshTokenDataSource
import com.nikpanfilov.network.token.data.datasource.RefreshTokenDataSourceImpl
import com.nikpanfilov.network.token.data.repository.RefreshTokenRepositoryImpl
import com.nikpanfilov.network.token.data.repository.TokenRepositoryImpl
import com.nikpanfilov.network.token.data.storage.SharedPrefsDataStorage
import com.nikpanfilov.network.token.data.storage.TokenDataStorage
import com.nikpanfilov.network.token.domain.repository.RefreshTokenRepository
import com.nikpanfilov.network.token.domain.repository.TokenRepository
import com.nikpanfilov.network.token.domain.usecase.LoadTokenUseCase
import com.nikpanfilov.network.token.domain.usecase.RefreshTokenUseCase
import com.nikpanfilov.network.token.domain.usecase.SaveTokenUseCase
import com.nikpanfilov.superfit.lab2.App
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val REFRESH = "refresh"

val tokenModule = module {

	single { LoadTokenUseCase(get()) }
	single { RefreshTokenUseCase(get()) }
	single { SaveTokenUseCase(get()) }

	single<TokenRepository> { TokenRepositoryImpl(get()) }
	single<RefreshTokenRepository> { RefreshTokenRepositoryImpl(get()) }

	single<TokenDataStorage> { SharedPrefsDataStorage(get()) }
	single<RefreshTokenDataSource> { RefreshTokenDataSourceImpl(get()) }

	single { createRetrofitService<RefreshTokenApi>(get(named(REFRESH))) }

	single(named(REFRESH)) {
		provideRefreshOkHttpClient(
			logging = get(named(LOG_INTERCEPTOR)),
			noConnection = get(named(NO_CONNECT_INTERCEPTOR)),
			token = get(named(TOKEN_INTERCEPTOR))
		)
	}
	single(named(REFRESH)) {
		provideRetrofit(
			okHttpClient = get(named(REFRESH)),
			moshi = get(),
			url = getProperty(App.BACKEND)
		)
	}
}