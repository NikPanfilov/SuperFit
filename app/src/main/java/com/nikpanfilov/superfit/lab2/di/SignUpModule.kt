package com.nikpanfilov.superfit.lab2.di

import com.nikpanfilov.network.createRetrofitService
import com.nikpanfilov.shared.login.data.repository.UserDataRepositoryImpl
import com.nikpanfilov.shared.login.data.storage.UserDataDataStorage
import com.nikpanfilov.shared.login.data.storage.UserDataDataStorageImpl
import com.nikpanfilov.shared.login.domain.repository.UserDataRepository
import com.nikpanfilov.shared.login.domain.usecase.SaveUserDataUseCase
import com.nikpanfilov.superfit.signup.data.api.SignUpApi
import com.nikpanfilov.superfit.signup.data.datasource.SignUpDataSource
import com.nikpanfilov.superfit.signup.data.datasource.SignUpDataSourceImpl
import com.nikpanfilov.superfit.signup.data.repository.SignUpRepositoryImpl
import com.nikpanfilov.superfit.signup.domain.repository.SignUpRepository
import com.nikpanfilov.superfit.signup.domain.usecase.SignUpUseCase
import com.nikpanfilov.superfit.signup.presentation.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val signUpModule = module {
	viewModel {
		SignUpViewModel(
			router = get(),
			signUpUseCase = get(),
			saveUserDataUseCase = get(),
			signInUseCase = get(),
			saveTokenUseCase = get(),
			getTokenUseCase = get()
		)
	}

	factory { createRetrofitService<SignUpApi>(get(named(ORIGINAL))) }

	factory<SignUpDataSource> { SignUpDataSourceImpl(get()) }
	factory<UserDataDataStorage> { UserDataDataStorageImpl(get()) }

	single<SignUpRepository> { SignUpRepositoryImpl(get()) }
	single<UserDataRepository> { UserDataRepositoryImpl(get()) }

	single { SignUpUseCase(get()) }
	single { SaveUserDataUseCase(get()) }
}