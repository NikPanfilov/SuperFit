package com.nikpanfilov.superfit.squats

import androidx.fragment.app.Fragment
import com.nikpanfilov.core.navigation.FragmentDestination
import com.nikpanfilov.superfit.squats.ui.SquatsFragment

class SquatsDestination : FragmentDestination {

	override fun createInstance(): Fragment = SquatsFragment.newInstance()
}