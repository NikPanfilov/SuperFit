package com.nikpanfilov.superfit.signup

import com.nikpanfilov.core.navigation.FragmentDestination
import com.nikpanfilov.superfit.signup.ui.SignUpFragment

class SignUpDestination : FragmentDestination {

	override fun createInstance() = SignUpFragment.newInstance()
}