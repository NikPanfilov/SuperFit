package com.nikpanfilov.superfit.lab2.navigation

import com.nikpanfilov.core.navigation.GlobalRouter
import com.nikpanfilov.superfit.main.MainDestination
import com.nikpanfilov.superfit.signin.PasswordDestination
import com.nikpanfilov.superfit.signin.presentation.LoginRouter
import com.nikpanfilov.superfit.signin.presentation.PasswordRouter
import com.nikpanfilov.superfit.signup.SignUpDestination

class LoginRouterImpl(
	private val router: GlobalRouter
) : LoginRouter {

	override fun navigateToPassword(login: String) {
		router.open(PasswordDestination(login))
	}

	override fun navigateToSignUp() {
		router.open(SignUpDestination())
	}
}

class PasswordRouterImpl(private val router: GlobalRouter) : PasswordRouter {

	override fun navigateBack() {
		router.exit()
	}

	override fun navigateToMain() {
		router.open(MainDestination())
	}
}