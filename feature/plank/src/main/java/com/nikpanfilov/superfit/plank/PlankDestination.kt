package com.nikpanfilov.superfit.plank

import androidx.fragment.app.Fragment
import com.nikpanfilov.core.navigation.FragmentDestination
import com.nikpanfilov.superfit.plank.ui.PlankFragment

class PlankDestination : FragmentDestination {

	override fun createInstance(): Fragment = PlankFragment.newInstance()
}