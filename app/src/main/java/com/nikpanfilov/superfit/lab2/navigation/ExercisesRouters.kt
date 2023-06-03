package com.nikpanfilov.superfit.lab2.navigation

import android.util.Log
import com.nikpanfilov.core.navigation.GlobalRouter
import com.nikpanfilov.shared.trainings.R
import com.nikpanfilov.superfit.crunch.presentation.CrunchRouter
import com.nikpanfilov.superfit.plank.presentation.PlankRouter
import com.nikpanfilov.superfit.pushups.presentation.PushUpsRouter
import com.nikpanfilov.superfit.result.ResultDestination
import com.nikpanfilov.superfit.running.presentation.RunningRouter
import com.nikpanfilov.superfit.squats.presentation.SquatsRouter

class RunningRouterImpl(private val router: GlobalRouter) : RunningRouter {

	override fun navigateToResult(left: Int) {
		router.open(ResultDestination(exercise = R.string.running, left = left))
	}

	override fun navigateBack() {
		router.exit()
	}
}

class CrunchRouterImpl(private val router: GlobalRouter) : CrunchRouter {

	override fun navigateToResult(left: Int) {
		router.open(ResultDestination(exercise = R.string.crunch, left = left))
	}

	override fun navigateBack() {
		router.exit()
	}
}

class PlankRouterImpl(private val router: GlobalRouter) : PlankRouter {

	override fun navigateToResult(left: Int) {
		Log.i("navigating", "impl")
		router.open(ResultDestination(exercise = R.string.plank, left = left))
	}

	override fun navigateBack() {
		router.exit()
	}
}

class PushUpsRouterImpl(private val router: GlobalRouter) : PushUpsRouter {

	override fun navigateToResult(left: Int) {
		router.open(ResultDestination(exercise = R.string.pushup, left = left))
	}

	override fun navigateBack() {
		router.exit()
	}
}

class SquatsRouterImpl(private val router: GlobalRouter) : SquatsRouter {

	override fun navigateToResult(left: Int) {
		router.open(ResultDestination(exercise = R.string.squat, left = left))
	}

	override fun navigateBack() {
		router.exit()
	}
}