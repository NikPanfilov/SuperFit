package com.nikpanfilov.shared.body.data.repository

import com.nikpanfilov.shared.body.data.api.PhotoApi
import com.nikpanfilov.shared.body.data.mapper.byteArrayToMultipartBody
import com.nikpanfilov.shared.body.data.mapper.toEntity
import com.nikpanfilov.shared.body.domain.entity.ProfilePhoto
import com.nikpanfilov.shared.body.domain.repository.ProfilePhotoRepository
import okhttp3.ResponseBody

class ProfilePhotoRepositoryImpl(private val api: PhotoApi) : ProfilePhotoRepository {

	override suspend fun getPhotos(): List<ProfilePhoto> = api.getPhotos().map { it.toEntity() }

	override suspend fun downloadPhoto(id: String): ResponseBody = api.downloadPhoto(id)

	override suspend fun setPhoto(photo: ByteArray) {
		api.setPhoto(byteArrayToMultipartBody(byteArray = photo, name = IMAGE_NAME, type = IMAGE_TYPE))
	}

	companion object {

		const val IMAGE_NAME = "file"
		const val IMAGE_TYPE = "image/jpeg"
	}
}