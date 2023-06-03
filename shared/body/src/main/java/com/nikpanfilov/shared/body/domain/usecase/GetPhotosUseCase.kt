package com.nikpanfilov.shared.body.domain.usecase

import com.nikpanfilov.shared.body.domain.entity.ProfilePhoto
import com.nikpanfilov.shared.body.domain.repository.ProfilePhotoRepository

class GetPhotosUseCase(private val repository: ProfilePhotoRepository) {

	suspend operator fun invoke(): List<ProfilePhoto> = repository.getPhotos()
}