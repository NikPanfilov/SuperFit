package com.nikpanfilov.shared.body.domain.usecase

import com.nikpanfilov.shared.body.domain.repository.ProfilePhotoRepository

class SetPhotoUseCase(private val repository: ProfilePhotoRepository) {

	suspend operator fun invoke(photo: ByteArray) {
		repository.setPhoto(photo)
	}
}