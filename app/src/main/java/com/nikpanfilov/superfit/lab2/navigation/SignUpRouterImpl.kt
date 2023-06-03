package com.nikpanfilov.superfit.lab2.navigation

import com.nikpanfilov.core.navigation.GlobalRouter
import com.nikpanfilov.superfit.main.MainDestination
import com.nikpanfilov.superfit.signin.LoginDestination
import com.nikpanfilov.superfit.signup.presentation.SignUpRouter

class SignUpRouterImpl(private val router: GlobalRouter) : SignUpRouter {

	override fun navigateToMain() {
		router.open(MainDestination())
	}

	override fun navigateToSignIn() {
		router.open(LoginDestination())
	}
}