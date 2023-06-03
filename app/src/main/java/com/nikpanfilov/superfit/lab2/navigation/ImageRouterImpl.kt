package com.nikpanfilov.superfit.lab2.navigation

import com.nikpanfilov.core.navigation.GlobalRouter
import com.nikpanfilov.superfit.image.presentation.ImageRouter

class ImageRouterImpl(private val router: GlobalRouter) : ImageRouter {

	override fun navigateBack() {
		router.exit()
	}
}