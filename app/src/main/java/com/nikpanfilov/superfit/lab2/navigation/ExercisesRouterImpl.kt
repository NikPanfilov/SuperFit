package com.nikpanfilov.superfit.lab2.navigation

import com.nikpanfilov.core.navigation.GlobalRouter
import com.nikpanfilov.superfit.crunch.CrunchDestination
import com.nikpanfilov.superfit.exercises.presentation.ExercisesRouter
import com.nikpanfilov.superfit.plank.PlankDestination
import com.nikpanfilov.superfit.pushups.PushUpsDestination
import com.nikpanfilov.superfit.running.RunningDestination
import com.nikpanfilov.superfit.squats.SquatsDestination

class ExercisesRouterImpl(private val router: GlobalRouter) : ExercisesRouter {

	override fun navigateBack() {
		router.exit()
	}

	override fun navigateToPushUps() {
		router.open(PushUpsDestination())
	}

	override fun navigateToPlank() {
		router.open(PlankDestination())
	}

	override fun navigateToSquats() {
		router.open(SquatsDestination())
	}

	override fun navigateToCrunch() {
		router.open(CrunchDestination())
	}

	override fun navigateToRunning() {
		router.open(RunningDestination())
	}
}