package com.nikpanfilov.superfit.result.presentation

import androidx.lifecycle.ViewModel

class ResultViewModel(private val router: ResultRouter) : ViewModel() {

	fun handle(event: ResultEvent) {
		when (event) {
			ResultEvent.NavigateToMain -> router.navigateToMain()
		}
	}
}