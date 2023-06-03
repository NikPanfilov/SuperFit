package com.nikpanfilov.superfit.lab2.presentation

import androidx.lifecycle.ViewModel
import com.nikpanfilov.network.token.domain.usecase.LoadTokenUseCase
import com.nikpanfilov.superfit.lab2.domain.usecase.CheckFirstStartUseCase

class MainActivityViewModel(
	private val router: MainActivityRouter,
	private val checkFirstStartUseCase: CheckFirstStartUseCase,
	private val loadTokenUseCase: LoadTokenUseCase,
) : ViewModel() {

	fun handle() {
		if (loadTokenUseCase().accessToken.isNotEmpty())
			router.navigateToMain()
		else {
			if (checkFirstStartUseCase())
				router.navigateToSignUp()
			else
				router.navigateToSignIn()
		}
	}
}