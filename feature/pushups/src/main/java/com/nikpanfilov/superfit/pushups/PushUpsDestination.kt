package com.nikpanfilov.superfit.pushups

import androidx.fragment.app.Fragment
import com.nikpanfilov.core.navigation.FragmentDestination
import com.nikpanfilov.superfit.pushups.ui.PushUpsFragment

class PushUpsDestination : FragmentDestination {

	override fun createInstance(): Fragment = PushUpsFragment.newInstance()
}