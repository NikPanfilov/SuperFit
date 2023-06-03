package com.nikpanfilov.superfit.lab2.di

import com.nikpanfilov.superfit.imagelist.presentation.ImageListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val imageListModule = module {
	viewModel {
		ImageListViewModel(
			router = get(),
			getPhotosUseCase = get(),
			downloadPhotoUseCase = get()
		)
	}
}