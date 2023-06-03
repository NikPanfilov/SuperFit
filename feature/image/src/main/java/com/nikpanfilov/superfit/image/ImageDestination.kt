package com.nikpanfilov.superfit.image

import android.graphics.Bitmap
import com.nikpanfilov.core.navigation.FragmentDestination
import com.nikpanfilov.superfit.image.ui.ImageFragment

class ImageDestination(private val image: Bitmap, private val date: String) : FragmentDestination {

	override fun createInstance() = ImageFragment.newInstance(image, date)
}