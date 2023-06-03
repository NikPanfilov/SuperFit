package com.nikpanfilov.superfit.exercises

import com.nikpanfilov.core.navigation.FragmentDestination
import com.nikpanfilov.superfit.exercises.ui.ExercisesFragment

class ExercisesDestination : FragmentDestination {

	override fun createInstance() = ExercisesFragment.newInstance()
}