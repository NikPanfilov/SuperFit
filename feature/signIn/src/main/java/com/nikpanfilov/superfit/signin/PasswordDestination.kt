package com.nikpanfilov.superfit.signin

import com.nikpanfilov.core.navigation.FragmentDestination
import com.nikpanfilov.superfit.signin.ui.PasswordFragment

class PasswordDestination(private val login: String) : FragmentDestination {

	override fun createInstance() = PasswordFragment.newInstance(login)
}