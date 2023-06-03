package com.nikpanfilov.superfit.mybody

import com.nikpanfilov.core.navigation.FragmentDestination
import com.nikpanfilov.superfit.mybody.ui.MyBodyFragment

class MyBodyDestination : FragmentDestination {

	override fun createInstance() = MyBodyFragment.newInstance()
}