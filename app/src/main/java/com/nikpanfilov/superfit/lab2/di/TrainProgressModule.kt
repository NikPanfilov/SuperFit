package com.nikpanfilov.superfit.lab2.di

import com.nikpanfilov.superfit.trainprogress.presentation.TrainProgressViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val trainProgressModule = module {
	viewModel {
		TrainProgressViewModel(
			router = get(),
			getTrainingsUseCase = get()
		)
	}
}