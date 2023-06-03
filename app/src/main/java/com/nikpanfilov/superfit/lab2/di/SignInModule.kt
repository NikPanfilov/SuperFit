package com.nikpanfilov.superfit.lab2.di

import com.nikpanfilov.network.createRetrofitService
import com.nikpanfilov.shared.login.data.api.SignInApi
import com.nikpanfilov.shared.login.data.datasource.SignInDataSource
import com.nikpanfilov.shared.login.data.datasource.SignInDataSourceImpl
import com.nikpanfilov.shared.login.data.repository.SignInRepositoryImpl
import com.nikpanfilov.shared.login.domain.repository.SignInRepository
import com.nikpanfilov.shared.login.domain.usecase.SignInUseCase
import com.nikpanfilov.superfit.signin.presentation.LoginViewModel
import com.nikpanfilov.superfit.signin.presentation.PasswordViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val signInModule = module {
	viewModel {
		LoginViewModel(router = get())
	}

	viewModel { (login: String) ->
		PasswordViewModel(
			login = login,
			router = get(),
			signInUseCase = get(),
			getTokenUseCase = get(),
			saveTokenUseCase = get(),
			saveUserDataUseCase = get()
		)
	}

	factory { createRetrofitService<SignInApi>(get(named(ORIGINAL))) }

	factory<SignInDataSource> { SignInDataSourceImpl(get()) }

	single<SignInRepository> { SignInRepositoryImpl(get()) }

	single { SignInUseCase(get()) }
}