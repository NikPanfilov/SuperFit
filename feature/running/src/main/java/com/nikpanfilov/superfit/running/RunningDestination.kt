package com.nikpanfilov.superfit.running

import androidx.fragment.app.Fragment
import com.nikpanfilov.core.navigation.FragmentDestination
import com.nikpanfilov.superfit.running.ui.RunningFragment

class RunningDestination : FragmentDestination {

	override fun createInstance(): Fragment = RunningFragment.newInstance()
}