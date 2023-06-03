package com.nikpanfilov.superfit.lab2.di

import com.nikpanfilov.superfit.main.data.repository.SignOutRepositoryImpl
import com.nikpanfilov.superfit.main.data.storage.SignOutDataStorage
import com.nikpanfilov.superfit.main.data.storage.SignOutDataStorageImpl
import com.nikpanfilov.superfit.main.domain.repository.SignOutRepository
import com.nikpanfilov.superfit.main.domain.usecase.SignOutUseCase
import com.nikpanfilov.superfit.main.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
	viewModel {
		MainViewModel(
			router = get(),
			signOutUseCase = get(),
			getLastBodyParamsUseCase = get(),
			getTrainingsUseCase = get()
		)
	}

	single { SignOutUseCase(get()) }
	single<SignOutRepository> { SignOutRepositoryImpl(get()) }
	single<SignOutDataStorage> { SignOutDataStorageImpl(get()) }
}