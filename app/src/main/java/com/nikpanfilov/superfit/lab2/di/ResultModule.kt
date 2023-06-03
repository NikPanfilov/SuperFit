package com.nikpanfilov.superfit.lab2.di

import com.nikpanfilov.superfit.result.presentation.ResultViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val resultModule = module {
	viewModel {
		ResultViewModel(
			router = get(),
		)
	}
}