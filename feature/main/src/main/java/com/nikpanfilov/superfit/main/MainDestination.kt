package com.nikpanfilov.superfit.main

import com.nikpanfilov.core.navigation.FragmentDestination
import com.nikpanfilov.superfit.main.ui.MainFragment

class MainDestination : FragmentDestination {

	override fun createInstance() = MainFragment.newInstance()
}