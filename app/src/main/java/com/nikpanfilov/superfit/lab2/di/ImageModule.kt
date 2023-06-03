package com.nikpanfilov.superfit.lab2.di

import com.nikpanfilov.superfit.image.presentation.ImageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val imageModule = module {
	viewModel {
		ImageViewModel(
			router = get()
		)
	}
}