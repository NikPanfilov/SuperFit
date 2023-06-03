package com.nikpanfilov.superfit.image.presentation

import androidx.lifecycle.ViewModel

class ImageViewModel(private val router: ImageRouter) : ViewModel() {

	fun handle(event: ImageEvent) {
		when (event) {
			ImageEvent.NavigateBack -> router.navigateBack()
		}
	}
}