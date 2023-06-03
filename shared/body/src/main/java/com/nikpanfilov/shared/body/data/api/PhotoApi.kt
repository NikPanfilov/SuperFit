package com.nikpanfilov.shared.body.data.api

import com.nikpanfilov.shared.body.data.dto.ProfilePhotoDto
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface PhotoApi {

	@GET("api/profile/photos")
	suspend fun getPhotos(): List<ProfilePhotoDto>

	@GET("api/profile/photos/{id}")
	suspend fun downloadPhoto(@Path("id") id: String): ResponseBody

	@Multipart
	@POST("api/profile/photos")
	suspend fun setPhoto(@Part photo: MultipartBody.Part)
}