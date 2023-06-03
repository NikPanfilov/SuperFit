package com.nikpanfilov.superfit.signin

import com.nikpanfilov.core.navigation.FragmentDestination
import com.nikpanfilov.superfit.signin.ui.LoginFragment

class LoginDestination : FragmentDestination {

	override fun createInstance() = LoginFragment.newInstance()
}