package com.nikpanfilov.superfit.lab2.navigation

import com.nikpanfilov.core.navigation.GlobalRouter
import com.nikpanfilov.superfit.imagelist.ImageListDestination
import com.nikpanfilov.superfit.mybody.presentation.MyBodyRouter
import com.nikpanfilov.superfit.statistics.StatisticsDestination
import com.nikpanfilov.superfit.trainprogress.TrainProgressDestination

class MyBodyRouterImpl(private val router: GlobalRouter) : MyBodyRouter {

	override fun navigateToImageList() {
		router.open(ImageListDestination())
	}

	override fun navigateToStatistics() {
		router.open(StatisticsDestination())
	}

	override fun navigateToTrainProgress() {
		router.open(TrainProgressDestination())
	}
}