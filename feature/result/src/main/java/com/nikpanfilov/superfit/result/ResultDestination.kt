package com.nikpanfilov.superfit.result

import androidx.annotation.StringRes
import com.nikpanfilov.core.navigation.FragmentDestination
import com.nikpanfilov.superfit.result.ui.ResultFragment

class ResultDestination(
	@StringRes private val exercise: Int,
	private val left: Int
) : FragmentDestination {

	override fun createInstance() = ResultFragment.newInstance(
		exercise = exercise,
		left = left
	)
}