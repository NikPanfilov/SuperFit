package com.nikpanfilov.superfit.lab2.navigation

import com.nikpanfilov.core.navigation.GlobalRouter
import com.nikpanfilov.superfit.trainprogress.presentation.TrainProgressRouter

class TrainProgressRouterImpl(private val router: GlobalRouter) : TrainProgressRouter {

	override fun navigateBack() {
		router.exit()
	}
}