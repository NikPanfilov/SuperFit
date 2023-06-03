package com.nikpanfilov.superfit.lab2.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.nikpanfilov.core.navigation.GlobalRouter
import com.nikpanfilov.superfit.lab2.data.repository.FirstStartRepositoryImpl
import com.nikpanfilov.superfit.lab2.data.storage.FirstStartDataStorage
import com.nikpanfilov.superfit.lab2.data.storage.FirstStartDataStorageImpl
import com.nikpanfilov.superfit.lab2.domain.repository.FirstStartRepository
import com.nikpanfilov.superfit.lab2.domain.usecase.CheckFirstStartUseCase
import com.nikpanfilov.superfit.lab2.navigation.GlobalRouterImpl
import com.nikpanfilov.superfit.lab2.navigation.GlobalRouterName.GLOBAL
import com.nikpanfilov.superfit.lab2.navigation.buildCicerone
import com.nikpanfilov.superfit.lab2.presentation.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {

	single(named(GLOBAL)) { buildCicerone() }
	single(named(GLOBAL)) { get<Cicerone<Router>>(named(GLOBAL)).router }
	single(named(GLOBAL)) { get<Cicerone<Router>>(named(GLOBAL)).getNavigatorHolder() }
	single<GlobalRouter> { GlobalRouterImpl(get(named(GLOBAL))) }

	viewModel {
		MainActivityViewModel(checkFirstStartUseCase = get(), router = get(), loadTokenUseCase = get())
	}

	single { CheckFirstStartUseCase(get()) }
	single<FirstStartRepository> { FirstStartRepositoryImpl(get()) }
	single<FirstStartDataStorage> { FirstStartDataStorageImpl(get()) }
}
