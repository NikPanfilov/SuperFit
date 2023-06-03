package com.nikpanfilov.superfit.statistics

import com.nikpanfilov.core.navigation.FragmentDestination
import com.nikpanfilov.superfit.statistics.ui.StatisticsFragment

class StatisticsDestination : FragmentDestination {

	override fun createInstance() = StatisticsFragment.newInstance()
}