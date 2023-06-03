package com.nikpanfilov.superfit.lab2.navigation

import com.nikpanfilov.core.navigation.GlobalRouter
import com.nikpanfilov.superfit.crunch.CrunchDestination
import com.nikpanfilov.superfit.exercises.ExercisesDestination
import com.nikpanfilov.superfit.main.presentation.MainRouter
import com.nikpanfilov.superfit.mybody.MyBodyDestination
import com.nikpanfilov.superfit.plank.PlankDestination
import com.nikpanfilov.superfit.pushups.PushUpsDestination
import com.nikpanfilov.superfit.running.RunningDestination
import com.nikpanfilov.superfit.signin.LoginDestination
import com.nikpanfilov.superfit.squats.SquatsDestination

class MainRouterImpl(private val router: GlobalRouter) : MainRouter {

	override fun navigateToLogin() {
		router.newRoot(LoginDestination())
	}

	override fun navigateToMyBody() {
		router.open(MyBodyDestination())
	}

	override fun navigateToExercises() {
		router.open(ExercisesDestination())
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