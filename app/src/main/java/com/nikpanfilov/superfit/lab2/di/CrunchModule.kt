package com.nikpanfilov.superfit.lab2.di

import com.nikpanfilov.superfit.crunch.presentation.CrunchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val crunchModule = module {
	viewModel { CrunchViewModel(router = get(), getTrainingsUseCase = get(), saveTrainingUseCase = get()) }
}