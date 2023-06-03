package com.nikpanfilov.superfit.lab2.navigation

import com.nikpanfilov.core.navigation.GlobalRouter
import com.nikpanfilov.superfit.lab2.presentation.MainActivityRouter
import com.nikpanfilov.superfit.main.MainDestination
import com.nikpanfilov.superfit.signin.LoginDestination
import com.nikpanfilov.superfit.signup.SignUpDestination

class MainActivityRouterImpl(
	private val router: GlobalRouter
) : MainActivityRouter {

	override fun navigateToSignIn() {
		router.open(LoginDestination())
	}

	override fun navigateToSignUp() {
		router.open(SignUpDestination())
	}

	override fun navigateToMain() {
		router.open(MainDestination())
	}
}