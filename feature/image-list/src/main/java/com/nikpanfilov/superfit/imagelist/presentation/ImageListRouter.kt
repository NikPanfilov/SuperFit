package com.nikpanfilov.superfit.imagelist.presentation

import android.graphics.Bitmap

interface ImageListRouter {

	fun navigateBack()
	fun navigateToImage(image: Bitmap, date: String)
}