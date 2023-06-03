package com.nikpanfilov.superfit.trainprogress

import com.nikpanfilov.core.navigation.FragmentDestination
import com.nikpanfilov.superfit.trainprogress.ui.TrainProgressFragment

class TrainProgressDestination : FragmentDestination {

	override fun createInstance() = TrainProgressFragment.newInstance()
}