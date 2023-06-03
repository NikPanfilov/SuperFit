package com.nikpanfilov.shared.body.domain.usecase

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.nikpanfilov.shared.body.domain.repository.ProfilePhotoRepository

class DownloadPhotoUseCase(private val repository: ProfilePhotoRepository) {

	suspend operator fun invoke(id: String): Bitmap? {
		val stream = repository.downloadPhoto(id).byteStream()
		val bitmap = BitmapFactory.decodeStream(stream)
		stream.close()
		return bitmap
	}
}