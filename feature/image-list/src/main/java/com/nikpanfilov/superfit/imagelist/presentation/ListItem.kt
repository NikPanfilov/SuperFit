package com.nikpanfilov.superfit.imagelist.presentation

import android.graphics.Bitmap

sealed class ListItem {
	data class PhotoItem(val photo: Bitmap, val date: String) : ListItem()
	data class DateItem(val date: String) : ListItem()
}