package com.nikpanfilov.superfit.lab2.di

import com.nikpanfilov.superfit.statistics.presentation.StatisticsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val statisticsModule = module {
	viewModel {
		StatisticsViewModel(
			router = get(),
			getParamsUseCase = get(),
			getTrainingsUseCase = get()
		)
	}
}