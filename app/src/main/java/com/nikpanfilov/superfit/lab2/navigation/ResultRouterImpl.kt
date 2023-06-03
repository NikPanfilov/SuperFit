package com.nikpanfilov.superfit.lab2.navigation

import com.nikpanfilov.core.navigation.GlobalRouter
import com.nikpanfilov.superfit.main.MainDestination
import com.nikpanfilov.superfit.result.presentation.ResultRouter

class ResultRouterImpl(private val router: GlobalRouter) : ResultRouter {

	override fun navigateToMain() {
		router.newRoot(MainDestination())
	}
}