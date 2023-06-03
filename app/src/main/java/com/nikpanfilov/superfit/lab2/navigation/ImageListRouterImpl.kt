package com.nikpanfilov.superfit.lab2.navigation

import android.graphics.Bitmap
import com.nikpanfilov.core.navigation.GlobalRouter
import com.nikpanfilov.superfit.image.ImageDestination
import com.nikpanfilov.superfit.imagelist.presentation.ImageListRouter

class ImageListRouterImpl(private val router: GlobalRouter) : ImageListRouter {

	override fun navigateBack() {
		router.exit()
	}

	override fun navigateToImage(image: Bitmap, date: String) {
		router.open(ImageDestination(image, date))
	}
}