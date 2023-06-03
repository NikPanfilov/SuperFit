package com.nikpanfilov.superfit.extensions

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun String.convertToBitmap(): Bitmap? {
	try {
		val imageData = Base64.decode(this, Base64.DEFAULT)
		return BitmapFactory.decodeByteArray(imageData, 0, imageData.size)
	} catch (e: Exception) {
		e.printStackTrace()
	}
	return null
}

fun getCurrentDate(): String {
	val currentDate = LocalDate.now()
	val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
	return currentDate.format(formatter)
}