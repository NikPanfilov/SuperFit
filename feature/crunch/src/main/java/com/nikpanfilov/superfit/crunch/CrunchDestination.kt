package com.nikpanfilov.superfit.crunch

import androidx.fragment.app.Fragment
import com.nikpanfilov.core.navigation.FragmentDestination
import com.nikpanfilov.superfit.crunch.ui.CrunchFragment

class CrunchDestination : FragmentDestination {

	override fun createInstance(): Fragment = CrunchFragment.newInstance()
}