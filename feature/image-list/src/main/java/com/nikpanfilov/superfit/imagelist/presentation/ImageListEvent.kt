package com.nikpanfilov.superfit.imagelist.presentation

import android.graphics.Bitmap

sealed class ImageListEvent {
	data class NavigateToImage(val image: Bitmap, val date: String) : ImageListEvent()
	object NavigateBack : ImageListEvent()
}
