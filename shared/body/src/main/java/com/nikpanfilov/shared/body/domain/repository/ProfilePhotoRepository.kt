package com.nikpanfilov.shared.body.domain.repository

import com.nikpanfilov.shared.body.domain.entity.ProfilePhoto
import okhttp3.ResponseBody

interface ProfilePhotoRepository {

	suspend fun getPhotos(): List<ProfilePhoto>

	suspend fun downloadPhoto(id: String): ResponseBody

	suspend fun setPhoto(photo: ByteArray)
}