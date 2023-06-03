package com.nikpanfilov.superfit.lab2.navigation

import com.nikpanfilov.core.navigation.GlobalRouter
import com.nikpanfilov.superfit.statistics.presentation.StatisticsRouter

class StatisticsRouterImpl(private val router: GlobalRouter) : StatisticsRouter {

	override fun navigateBack() {
		router.exit()
	}
}