package com.nikpanfilov.superfit.imagelist

import com.nikpanfilov.core.navigation.FragmentDestination
import com.nikpanfilov.superfit.imagelist.ui.ImageListFragment

class ImageListDestination : FragmentDestination {

	override fun createInstance() = ImageListFragment.newInstance()
}