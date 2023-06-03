package com.nikpanfilov.superfit.lab2.di

import com.nikpanfilov.superfit.mybody.presentation.MyBodyViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myBodyModule = module {
	viewModel {
		MyBodyViewModel(
			router = get(),
			getLastBodyParamsUseCase = get(),
			setBodyParamsUseCase = get(),
			getPhotosUseCase = get(),
			downloadPhotoUseCase = get(),
			setPhotoUseCase = get()
		)
	}
}