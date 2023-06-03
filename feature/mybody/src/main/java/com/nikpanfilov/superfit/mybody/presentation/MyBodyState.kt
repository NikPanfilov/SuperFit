package com.nikpanfilov.superfit.mybody.presentation

import android.graphics.Bitmap
import com.nikpanfilov.shared.body.domain.entity.Body

sealed class MyBodyState {
	object Initial : MyBodyState()

	data class Content(
		val body: Body?,
		val firstImage: Bitmap?,
		val lastImage: Bitmap?,
		val firstImageTimeStamp: Long,
		val lastImageTimeStamp: Long
	) : MyBodyState()
}
